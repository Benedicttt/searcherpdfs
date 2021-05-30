import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        Path dir = Path.of("src/main/resources/pdfs/");

        DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{pdf}");

        int i = 0;
        for (Path entry : stream) {
            i++;

            PDDocument document = PDDocument.load(new File(entry.toString()));
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);

                System.out.println(i + " texts):\n" + text + "\n");
            }
            document.close();

        }
    }
}
