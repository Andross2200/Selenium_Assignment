package util;

import jakarta.mail.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Properties;

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
