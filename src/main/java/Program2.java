import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.*;
import java.util.Scanner;
import java.io.File;


public class Program2 {
    static String fileLog = "log.txt";
    static String fileResult = "result.txt";
    public static final Logger logger = Logger.getLogger(
            Program2.class.getName());

    public static void initLogger()
    {
        Handler fileHandler = null;
        Formatter simpleFormatter = null;
        try{
            fileHandler = new FileHandler(fileLog);
            simpleFormatter = new SimpleFormatter();

            logger.addHandler(fileHandler);
            fileHandler.setFormatter(simpleFormatter);

            fileHandler.setLevel(Level.ALL);
            logger.setLevel(Level.ALL);
        }
        catch (IOException e){
            logger.log(Level.SEVERE, "Ошибка: ", e);
        }
    }

    public static String validate(String text){
        int i;
        logger.info("Проверка введенной строки");
        try {
            i = Integer.parseInt(text);
        }
        catch (NumberFormatException e){
            logger.log(Level.SEVERE, "Ошибка: ", e);
            return "Введенное значение не является числом.";
        }

        if((i > 0 && i <256) || (i >= -128 && i <= 127)){
            return text;
        }
        else{
            return String.format("Введенное число %s не явяется типом Byte", text);
        }
    }

    public static void main(String[] args){
        initLogger();

        logger.info("Application started and constructed.");

        File fr = new File(fileResult);
        try{
            logger.info("Проверка наличия файла");
            if(!fr.exists())
                fr.createNewFile();
        }
        catch (IOException error){
            logger.log(Level.SEVERE, "Ошибка создания файла. " + error);
        }

        PrintWriter writeFL;
        try {
            logger.info("Открытие потока на запись в файл");
            writeFL = new PrintWriter(fr);
            Scanner inp = new Scanner(System.in);
            System.out.print("Введите число: ");
            String line = validate(inp.nextLine());

            writeFL.println(line);
            logger.info("Закрытие файла");
            writeFL.close();
        }
        catch (IOException error){
            System.out.println("Невозможно открыть файл. Ошибка: " + error);
        }
    }
}
