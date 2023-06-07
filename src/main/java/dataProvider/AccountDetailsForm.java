package dataProvider;

public class AccountDetailsForm {

        private String firstName;
        private String lastName;
        private String displayName;
        private String emailAddress;

        public AccountDetailsForm(String firstName, String lastName, String displayName, String emailAddress) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.displayName = displayName;
            this.emailAddress = emailAddress;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }
}
