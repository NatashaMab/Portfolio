package website1.TS_Register;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.FromStringTerm;
import jakarta.mail.search.SearchTerm;

public class TC_RF_002 {

	@Test
	public void VerifyingEmail() {
	
		WebDriver driver = new ChromeDriver();
		
		//global wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//open browser 
		driver.manage().window().maximize();
		
		// go to website 
		driver.get("https://www.amazon.in/");
		
		//START NAVIGATION
		driver.findElement(By.xpath("//span[text() = 'Hello, sign in']")).click();
		//driver.findElement(By.xpath("//span[contains(text(),'Need help')]")).click();
		//driver.findElement(By.xpath("//*[@id=\"auth-fpp-link-bottom\"]")).click();
		
		String email = "contact.lightlineco@gmail.com";
		String appPasscode = "gsnj hohw blqe qasm";
		
		//VERIFICATION - access your email account
		
		driver.findElement(By.xpath("//*[@id=\"ap_email_login\"]")).sendKeys("contact.lightlineco@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"continue\"]/span/input")).click();
		
		 // Gmail IMAP settings
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        try {
            // Create a session
            Session session = Session.getDefaultInstance(props, null);

            // Connect to the Gmail store
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", email, appPasscode);

            // Open the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            
         // Search for emails from a specific sender
            SearchTerm senderSearchTerm = new FromStringTerm("noreply@amazon.in");
            Message[] messages = inbox.search(senderSearchTerm);
            

            System.out.println("Total Messages: " + messages.length);

            for (int i = messages.length - 1; i >= messages.length ; i--) { // Read last 5 emails
                Message msg = messages[i];
                System.out.println("\nEmail #" + (i + 1));
                System.out.println("Subject: " + msg.getSubject());
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("Body: " + getTextFromMessage(msg));
            }

            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

	// Extract text from message
	private static String getTextFromMessage(Message message) throws Exception {
	    if (message.isMimeType("text/plain")) {
	        return message.getContent().toString();
			    } else if (message.isMimeType("multipart/*")) {
				        Multipart multipart = (Multipart) message.getContent();
				        StringBuilder result = new StringBuilder();
				        for (int i = 0; i < multipart.getCount(); i++) {
				            BodyPart bodyPart = multipart.getBodyPart(i);
				            if (bodyPart.isMimeType("text/plain")) {
				                result.append(bodyPart.getContent());
				            }
			        }
			        return result.toString();
			    }
			    return "";
	}
}

