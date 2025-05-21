package util;

import jakarta.mail.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Properties;

/**
 * This class establishes connection to yandex email account andre.shamnaev@yandex.ru.
 * The class is capable of extracting unseen messages received by this email address.
 */
public class EmailUtil {
    private final Properties props;
    private final Session session;
    private final Store store;
    private final Folder folder;

    public EmailUtil() {
        this.props = System.getProperties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imaps.partialfetch", "false");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.mime.base64.ignoreerrors", "true");
        MyConfig config = new MyConfig();

        try {
            this.session = Session.getDefaultInstance(props, null);
            this.store = session.getStore("imap");
            this.store.connect(config.getProperty("mail.imap"), 993, config.getProperty("mail.address"), config.getProperty("mail.password"));
            this.folder = store.getFolder("Inbox");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method return a body of an unseen email received from given sender with given subject line.
     * If there is not matching email, an empty string is returned.
     * @param sender
     * @param subject
     * @return String body of an email or an empty string
     */
    public String getNewEmailBody(String sender, String subject) throws MessagingException {
        String emailBody = "empty";
        this.folder.open(Folder.READ_WRITE);
        Message[] emails = this.folder.getMessages();
        for (Message email : emails) {
            if(!email.isSet(Flags.Flag.SEEN) &&
                    email.getFrom()[0].toString().contains(sender) &&
                    email.getSubject().contains(subject)) {
                try {
                    emailBody = getEmailBody(email);
                    email.setFlag(Flags.Flag.SEEN, true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        this.folder.close();

        return emailBody;
    }

    private String getEmailBody(Message email) throws MessagingException, IOException {
        StringBuilder bufferEmailContentEncoded = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            bufferEmailContentEncoded.append(line);
        }

        String emailContentEncoded = bufferEmailContentEncoded.toString();
        if (email.getContentType().toLowerCase().contains("multipart/related")) {

            emailContentEncoded = emailContentEncoded.substring(emailContentEncoded.indexOf("base64") + 6);
            emailContentEncoded = emailContentEncoded.substring(0, emailContentEncoded.indexOf("Content-Type") - 1);

            System.out.println(emailContentEncoded);

            return new String(Base64.getDecoder().decode(emailContentEncoded.getBytes()));
        }

        return emailContentEncoded;
    }
}
