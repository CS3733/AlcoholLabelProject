package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by keionbis on 4/21/17.
 */
public class PasswordStrengthChecker {

    private int Rating;
    private int length;
    private char currChar;
    private int needs;
    private int strength;
    private int totalSum;

//    public int StrengthChecker(String Password, int currRaring){
//        this.Rating = currRaring;
//        length = Password.length();
//        currChar = Password.charAt(length);
//
//    }
    enum PasswordStates{
        NEEDSUPPERCASE,
        NEEDSLOWERCASE,
        NEEDSDIGIT,
        NEEDSSYMBOL,
        GOODTOGO,
        NEEDSALL,
        NEEDSUPPERCASEANDLOWERCASE,
        NEEDSUPPERCASEANDDIGIT,
        NEEDSUPPERCASEANDSYMBOL,
        NEEDSLOWERCASEANDDIGIT,
        NEEDSLOWERCASEANDSYMBOL,
        NEEDSDIGITANDSYMBOL,
        NEEDSUPPERCASEDIGITANDSYMBOL,
        NEEDSUPPERCASELOWERCASESYMBOL,
        NEEDSUPPERCASELOWERCASEDIGIT,
        NEEDSLOWERCASEDIGITSYMBOL,
        INVALIDHASSPACE
    };


    public PasswordStates whatsneeded(){
            PasswordStates result;
            switch (getNeeds()) {
                case 0:
                    result = PasswordStates.GOODTOGO;
                    break;
                case 1:
                    result = PasswordStates.NEEDSUPPERCASE;
                    break;
                case 2:
                    result = PasswordStates.NEEDSLOWERCASE;
                    break;
                case 3:
                    result = PasswordStates.NEEDSUPPERCASEANDLOWERCASE;
                    break;
                case 4:
                    result = PasswordStates.NEEDSDIGIT;
                    break;
                case 5:
                    result = PasswordStates.NEEDSUPPERCASEANDDIGIT;
                    break;
                case 6:
                    result = PasswordStates.NEEDSLOWERCASEANDDIGIT;
                    break;
                case 7:
                    result = PasswordStates.NEEDSUPPERCASELOWERCASEDIGIT;
                    break;
                case 8:
                    result = PasswordStates.NEEDSSYMBOL;
                    break;
                case 9:
                    result = PasswordStates.NEEDSUPPERCASEANDSYMBOL;
                    break;
                case 10:
                    result = PasswordStates.NEEDSLOWERCASEANDSYMBOL;
                    break;
                case 11:
                    result = PasswordStates.NEEDSUPPERCASELOWERCASESYMBOL;
                    break;
                case 12:
                    result = PasswordStates.NEEDSDIGITANDSYMBOL;
                    break;
                case 13:
                    result = PasswordStates.NEEDSUPPERCASEDIGITANDSYMBOL;
                    break;
                case 14:
                    result = PasswordStates.NEEDSLOWERCASEDIGITSYMBOL;
                    break;
                case 15:
                    result = PasswordStates.NEEDSALL;
                    break;
                case 16:
                    result = PasswordStates.INVALIDHASSPACE;
                    break;
                default:
                    result = PasswordStates.INVALIDHASSPACE;
            }
            return result;
        }

    public boolean isPasswordValid(String password) {
        this.strength = 0;
        this.needs = 0;
        this.totalSum = 0;
       if(!hasSpace(password)&&hasSylbol(password)&&hasDigit(password)&&hasLowerCase(password)&&hasUpperCase(password)&& isValidLength(password)){
               return true;
       }
        return false;
    }

    public int getNeeds() {
        return this.needs;
    }

    public void setNeeds(int needs) {
        this.needs = needs;
    }

    public boolean hasUpperCase(String password) {
        for (int i = 0; i < (password.length()); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLowerCase(String password) {
        for (int i = 0; i < (password.length()); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDigit(String password) {
        for (int i = 0; i < (password.length()); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSylbol(String password) {
        for (int i = 0; i < (password.length()); i++) {
            if ((password.charAt(i) == '!') || (password.charAt(i) == '@') || (password.charAt(i) == '#') || (password.charAt(i) == '$') || (password.charAt(i) == '%') || (password.charAt(i) == '^') || (password.charAt(i) == '&') || (password.charAt(i) == '*') || (password.charAt(i) == '(') || (password.charAt(i) == ')')) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSpace(String password) {
        for (int i = 0; i < (password.length()); i++) {
            if (Character.isSpaceChar(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    public boolean isValidLength(String password){
        if(password.length()<7)
        {
            return false;
        }
        return true;
    }

}
