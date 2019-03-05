export class SignUpInfo {
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    interests:string[];

    constructor(email: string, password: string, firstName: string, lastName: string,interests:string[]) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.interests = interests;
    }
}
