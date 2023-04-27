import java.io.*;

class FileUtils {
    static String readFile(String filePath) throws IOException {
        String fileAsString = "";
        try (
                var reader = new BufferedReader(new FileReader(filePath));
        ) {
            boolean first = true;
            String nextLine = null;
            while ((nextLine = reader.readLine()) != null) {
                if (!first) {
                    fileAsString += "\n";
                } else {
                    first = false;
                }
                fileAsString += nextLine;
            }
        } catch (IOException e) {
            throw new IOException("Nie udało się wczytać pliku " + filePath);
        }

        return fileAsString;
    }

    static int countLines(String filePath) throws IOException {
        int lines = 0;
        try (
                var reader = new BufferedReader(new FileReader(filePath));
        ) {
            String nextLine = null;
            while ((nextLine = reader.readLine()) != null) {
                lines++;
            }
        } catch (IOException e) {
            throw new IOException("Nie udało się wczytać pliku " + filePath);
        }

        return lines;
    }

    static void writeToFile(String filePath, String text) throws IOException {
        try (
                var writer = new BufferedWriter(new FileWriter(filePath));
        ) {
            writer.write(text);
        } catch (IOException e) {
            throw new IOException("Zapis do pliku" + filePath + " nie powiódł się");
        }
    }

    static void createFile(String fileName) throws IOException {
        File file = new File(fileName);

        boolean fileExists = file.exists();
        if (!fileExists) {
            try {
                fileExists = file.createNewFile();
            } catch (IOException e) {
                throw new IOException("Nie udało się stworzyć pliku");
            }
        }

        if (fileExists) {
            System.out.println("Plik " + fileName + " istnieje lub został utworzony");
        }
    }
}
