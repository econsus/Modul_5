package Kegiatan_1;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    static void menuLoop() {
        Scanner scanner = new Scanner(System.in);
        int a = 0;
        while (a == 0) {
            int choice;
            System.out.println("============");
            System.out.println("====Menu====");
            System.out.println("1. Input Data");
            System.out.println("2. Show Data");
            System.out.println("3. Exit");
            choice = scanner.nextInt();
            switch (choice){
                case 1-> inputData();
                case 2-> showData();
                case 3-> a++;
                default -> System.out.println("Choose one of the menu");
            }
        }
    }

    static void inputData() {
        dinasPertanahan dataTanah = new dinasPertanahan();
        String inputAlamat;
        int inputPanjang, inputLuas, existance;
        Scanner scan = new Scanner(System.in);
            System.out.println("Input the address :");
            inputAlamat = scan.nextLine();
            dataTanah.setAlamat(inputAlamat);
            existance = fileCreate(dataTanah);
            if(existance == 0) {
                System.out.println("Input the length :");
                inputPanjang = scan.nextInt();
                dataTanah.setPanjangTanah(inputPanjang);
                System.out.println("Input the width :");
                inputLuas = scan.nextInt();
                dataTanah.setLuasTanah(inputLuas);
                fileWrite(existance, dataTanah);
            }
    }

    static int fileCreate(dinasPertanahan data) {
        int isAlreadyExist = 0;
        try {

            File file = new File(data.getAlamat() + ".txt");

            if(file.createNewFile()) {
                System.out.println("File named " + data.getAlamat() + ".txt" + " has been successfully created.");
                isAlreadyExist = 0;
            }
            else {
                System.out.println("File named " + data.getAlamat() + ".txt" + " already exist.");
                isAlreadyExist = 1;
            }
        }
        catch (IOException e) {
            System.out.println("Don't ask me why, it just happen.");
            e.printStackTrace();
        }
        return isAlreadyExist;
    }

    static void fileWrite(int isAlreadyExist, dinasPertanahan data) {
        if(isAlreadyExist == 0) {
            try {
                FileWriter writer = new FileWriter(data.getAlamat() + ".txt");
                writer.write("Alamat: ");
                writer.write(data.getAlamat());
                writer.write(System.getProperty("line.separator"));
                writer.write("Panjang: ");
                writer.write(Integer.toString(data.getPanjangTanah()));
                writer.write(System.getProperty("line.separator"));
                writer.write("Luas :");
                writer.write(Integer.toString(data.getLuasTanah()));
                writer.close();
            }
            catch (IOException e) {
                System.out.println("Don't ask me why, it just happen.");
                e.printStackTrace();
            }
        }
    }

    static void showData() {
        Scanner fileSearch = new Scanner(System.in);
        try {
            System.out.println("What file are you looking for?");
            String  fileName = fileSearch.nextLine();
            File readfile = new File(fileName + ".txt");
            Scanner reader = new Scanner(readfile);
            while (reader.hasNextLine()) {
                String read = reader.nextLine();
                System.out.println(read);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error 404....I don't think I need to explain this one.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        try {
            menuLoop();
        }
        catch (NullPointerException e) {
            System.out.println("Insert something you dimwit.");
        }
        catch (InputMismatchException e) {
            System.out.println("It clearly said put the length/width and you put a letter in it? Not very bright aren't you?");
        }
        finally {
            System.out.println("This is a finally statement.");
        }

    }

}
