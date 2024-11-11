package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import SetUp.SetUp;
import org.json.JSONArray;
import org.json.JSONObject;

public class Email_create_verify extends SetUp {

    public static String generateRandomEmail() {
        String username = "test" + System.currentTimeMillis();
        String domain = "1secmail.com";  // Use one of the 1secmail domains
        return username + "@" + domain;
    }

    public static void verifyEmail(String email) throws Exception {
        String[] emailParts = email.split("@");
        String inboxUrl = "https://www.1secmail.com/api/v1/?action=getMessages&login=" + emailParts[0] + "&domain=" + emailParts[1];
        JSONArray messages = new JSONArray();
        int retryCount = 5;  // Number of retries
        int waitTime = 5000; // Wait time between retries (5 seconds)

        for (int i = 0; i < retryCount; i++) {
            System.out.println("Attempt " + (i + 1) + ": Checking for email in inbox...");
            messages = getJsonArrayResponse(inboxUrl);
            System.out.println("Inbox Response: " + messages.toString()); // Log API response

            if (messages.length() > 0) {
                System.out.println("Email found on attempt " + (i + 1));
                break; // Exit loop if we find an email
            }
            Thread.sleep(waitTime); // Wait before retrying
        }

        if (messages.length() == 0) {
            throw new IllegalStateException("No emails found for verification.");
        }

        // Process the email message as before
        // Change from getString("id") to getInt("id") and then convert to String if needed
        int messageId = messages.getJSONObject(0).getInt("id");
        String messageIdStr = String.valueOf(messageId); // Convert to String if required for further processing
        String messageUrl = "https://www.1secmail.com/api/v1/?action=readMessage&login=" + emailParts[0] + "&domain=" + emailParts[1] + "&id=" + messageIdStr;

        JSONObject emailContent = getJsonResponse(messageUrl);

        String body = emailContent.getString("body");
        int linkStart = body.indexOf("https://");  // Start of link pattern

        if (linkStart == -1) {
            throw new IllegalStateException("Verification link not found in email content.");
        }

        int linkEnd = body.indexOf(" ", linkStart); // Adjust this to match the end of your URL pattern
        if (linkEnd == -1) {
            linkEnd = body.length(); // If there's no space, assume the link runs to the end
        }

        String verificationLink = body.substring(linkStart, linkEnd);
        System.out.println("Verification Link: " + verificationLink);


//        String body = emailContent.getString("body");
//        String verificationLink = extractVerificationLink(body);
//        System.out.println("Verification Link: " + verificationLink);

        driver.get(verificationLink); // Use WebDriver to navigate to the verification link
    }



    private static JSONArray getJsonArrayResponse(String urlString) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return new JSONArray(response.toString());
    }

    private static JSONObject getJsonResponse(String urlString) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return new JSONObject(response.toString());
    }

    private static String extractVerificationLink(String content) {
        int linkStart = content.indexOf("https://www.xampro.org/login");
        int linkEnd = content.indexOf(" ", linkStart);
        return content.substring(linkStart, linkEnd).trim();
    }
}
