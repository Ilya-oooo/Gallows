import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Gallows {

    private static Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private int countWordInFile;
    private String word;
    private int countMistake;
    private char letterRequest;
    private Set<Character> setOfMistake = new HashSet<>();
    private char[] arrWord;
    private boolean isFinishedGame = false;

    public static int startAndLeave(){
        System.out.println("Нажмите 1, чтобы начать новую игру или 2, чтобы выйти: ");
        return scanner.nextInt();
    }
    public void startGame(){
        System.out.println("Начало игры: ");
        System.out.println();
        while(!isFinishedGame){
            countMistake();
            createBoard();
            wordPrint();
            mistakesPrint();
            finishGame();
            letterRequest();
            if(isFinishedGame){
                return;
            }
        }
    }
    public void createBoard(){
        switch (countMistake){
            case 0 -> {
                System.out.println("--------------------\n" +
                        "            {----}  \n" +
                        "            |    |  \n" +
                        "                 |  \n" +
                        "                 |  \n" +
                        "                 |  \n" +
                        "                 |  \n");
            }
            case 1 -> {
                System.out.println("--------------------\n" +
                        "            {----}  \n" +
                        "            |    |  \n" +
                        "            o    |  \n" +
                        "                 |  \n" +
                        "                 |  \n" +
                        "                 |  \n");
            }
            case 2 -> {
                System.out.println("--------------------\n" +
                        "            {----}  \n" +
                        "            |    |  \n" +
                        "            o_   | \n" +
                        "                 |  \n" +
                        "                 |  \n" +
                        "                 |  \n");
            }
            case 3 -> {
                System.out.println("--------------------\n" +
                        "            {----}  \n" +
                        "            |    |  \n" +
                        "           _o_   |  \n" +
                        "                 |  \n" +
                        "                 |  \n" +
                        "                 |  \n");
            }
            case 4 -> {
                System.out.println("--------------------\n" +
                        "            {----}  \n" +
                        "            |    |  \n" +
                        "           _o_   |  \n" +
                        "            O    |  \n" +
                        "                 |  \n" +
                        "                 |  \n");
            }
            case 5 -> {
                System.out.println("--------------------\n" +
                        "            {----}  \n" +
                        "            |    |  \n" +
                        "           _o_   |  \n" +
                        "            O    |  \n" +
                        "             \\   |  \n" +
                        "                 |  \n");
            }
            case 6 -> {
                System.out.println("--------------------\n" +
                        "            {----}  \n" +
                        "            |    |  \n" +
                        "           _o_   |  \n" +
                        "            O    |  \n" +
                        "           / \\   |  \n" +
                        "                 |  \n");
            }
        }
    }

    private String generateRandomWord(){
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Словарь\\Слова.txt"));
            BufferedReader reader2 = new BufferedReader(new FileReader("C:\\Словарь\\Слова.txt"))){
            countWordInFile = 0;
            while(reader.readLine() != null){
                countWordInFile++;
            }
            int randomInt = (int) (random.nextDouble() * ++countWordInFile);
            countWordInFile--;
            int i = 0;
            while(randomInt >= i){
                i++;
                word = reader2.readLine().toLowerCase();
                if(randomInt == i)
                    break;
            }
        }

        catch (IOException e){
            e.getStackTrace();
        }
        arrWord = new char[word.length()];
        return word;
    }

    public void wordPrint(){
        rightWord();
        for (int i = 0; i < arrWord.length; i++) {
            System.out.print(arrWord[i] + " ");
        }
        System.out.println();
    }

    private void rightWord(){
        for (int i = 0; i < arrWord.length; i++) {
            if(letterCheck(i)){
                arrWord[i] = letterRequest;
            }
            else if (arrWord[i] == '\0') arrWord[i] = '_';
        }
    }

    private boolean letterCheck(int i){
        return Character.toLowerCase(word.charAt(i)) == letterRequest;
    }

    private void countMistake(){
        if(letterRequest  == '\0') {
            return;
        }
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letterRequest) {
                //isCorrectLetter = true;
                return;
            }
        }
        mistake();
    }

    public void letterRequest(){
        if(!isFinishedGame) {
            System.out.println("Буква: ");
            letterRequest = Character.toLowerCase(scanner.next().charAt(0));
            if(!(letterRequest >= 1072 && letterRequest <= 1103)) {
                letterRequest = '\0';
                System.out.println("Введен некорректный символ " + '\u6a01');
            }

        }
        //isCorrectLetter = false;
    }

    private void mistake(){
        //if(!isCorrectLetter) {
        if(!setOfMistake.contains(letterRequest)) {
            countMistake++;
            setOfMistake.add(letterRequest);
        }
        //}
    }

    public void mistakesPrint(){
        System.out.print("Ошибки: " );
//        if(letterRequest  == '\0') {
//            return;
//        }
        for (Character c : setOfMistake){
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private boolean checkFinishGameLose(){
        if(countMistake == 6)
            return true;
        return false;
    }

    private boolean checkFinishGameWin(){
        for (int i = 0; i < arrWord.length; i++) {
            if(arrWord[i] == '_'){
                return false;
            }
        }
        return true;
    }

    private void finishGame(){
        if(checkFinishGameWin()){
            System.out.println("Вы выиграли!");
            isFinishedGame =  true;
            return;
        }
        if(checkFinishGameLose()){
            System.out.print("Вы проиграли! Загадано слово - ");
            for (int i = 0; i < word.length(); i++) {
                System.out.print(word.charAt(i));
            }
            System.out.println();
            isFinishedGame = true;
        }
    }
}
