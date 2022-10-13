
import java.util.Objects;
import java.util.Scanner;

public class Calc {
    static int convertToArabic(String ourNumber) {
        int result = 0;
        for (int i = 0; i < ourNumber.length(); i++) {
            if (ourNumber.length() >= 2) {
                if (ourNumber.charAt(i) == 'I' && i == 0 && ourNumber.charAt(i + 1) == 'X') {
                    result = 9;
                    return result;
                }
                else if (ourNumber.charAt(i) == 'I' && i == 0 && ourNumber.charAt(i + 1) == 'V') {
                    result = 4;
                    return result;
                } else {
                    switch (ourNumber.charAt(i)) {
                        case 'I':
                            result += 1;
                            break;
                        case 'V':
                            result += 5;
                            break;
                        case 'X':
                            result += 10;
                            break;
                        default:
                            return 0;
                    }
                }
            } else {
                return switch (ourNumber.charAt(i)) {
                    case 'I' -> 1;
                    case 'V' -> 5;
                    case 'X' -> 10;
                    default -> 0;
                };
            }
        }


        return result;
    }

    static String convertToRoman(int ourResult) {
        String[] usableNumbers = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] usableIntNumbers = {1, 4, 5, 9, 10, 40, 50, 90, 100};
        //1 4 5 9 10 40 50 90 100
        StringBuilder romanResult = new StringBuilder();

        while (ourResult > 0) {
            for (int i = usableIntNumbers.length - 1; i >= 0; i--) {
                while (ourResult - usableIntNumbers[i] >= 0) {
                    romanResult.append(usableNumbers[i]);
                    ourResult -= usableIntNumbers[i];
                }
            }
        }
        return romanResult.toString();
    }

    static boolean isRoman(String ourNumber) {
        return ourNumber.contains("I") || (ourNumber.contains("V")) || (ourNumber.contains("X"));
    }

    static boolean isArabic(String ourNumber) {
        try {
            Integer.parseInt(ourNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static int mathOperation(int firstNumber, int secondNumber, String operator, boolean isArabic) throws Exception {
        switch (operator){
            case "+":
                return firstNumber + secondNumber;
            case "-":
                if (isArabic) {
                    return firstNumber - secondNumber;
                }
                else {
                    if (firstNumber - secondNumber <= 0) {
                        throw new Exception();
                    } else {
                        return firstNumber - secondNumber;
                    }
                }
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }

    static BothType typeOfBoth(String firstNumber, String secondNumber) {
        if (isArabic(firstNumber) && isArabic(secondNumber)) {
            return BothType.ARABIC;
        } else if (isRoman(firstNumber) && isRoman(secondNumber)) {
            return BothType.ROMAN;
        } else {
            return BothType.ERROR;
        }

    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String mainString = scanner.nextLine();
        String[] splitString = mainString.split(" ");

        if (splitString.length == 3 && (Objects.equals(splitString[1], "+") || Objects.equals(splitString[1], "-") || Objects.equals(splitString[1], "*") || Objects.equals(splitString[1], "/"))) {
            switch (typeOfBoth(splitString[0], splitString[2])) {
                case ARABIC:
                    if ((Integer.parseInt(splitString[0]) >= 1 && Integer.parseInt(splitString[0]) <= 10) && (Integer.parseInt(splitString[2]) >= 1 && Integer.parseInt(splitString[2]) <= 10)) {
                        System.out.println(mathOperation(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[2]), splitString[1],true));
                        break;
                    } else {
                        throw new Exception();
                    }
                case ROMAN:
                    if ((convertToArabic(splitString[0]) >= 1 && convertToArabic(splitString[0]) <= 10) && (convertToArabic(splitString[2]) >= 1 && convertToArabic(splitString[2]) <= 10)) {
                        System.out.println(convertToRoman(mathOperation(convertToArabic(splitString[0]), convertToArabic(splitString[2]), splitString[1],false)));
                        break;
                    } else {
                        throw new Exception();
                    }
                case ERROR:
                    throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }
}

enum BothType {
    ARABIC,
    ROMAN,
    ERROR
}