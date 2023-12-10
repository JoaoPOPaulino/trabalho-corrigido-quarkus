package br.unitins.topicos1.service.quarto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jboss.logging.Logger;

import br.unitins.topicos1.resource.UsuarioResource;
import br.unitins.topicos1.service.FileService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuartoFileService implements FileService {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    private final String PATH_USER = System.getProperty("user.home") +
            File.separator + "quarkus" +
            File.separator + "images" +
            File.separator + "quartos" + File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png",
            "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10mb

    @Override
    public String salvar(String nomeArquivo, byte[] arquivo) throws IOException {
        LOGGER.info("Iniciando processo de salvamento do arquivo.");
        verificarTamanhoImagem(arquivo);
        verificarTipoImagem(nomeArquivo);

        Path diretorio = Paths.get(PATH_USER);
        Files.createDirectories(diretorio);

        Path filePath;
        String novoNomeArquivo;

        do {
            String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
            String extensao = mimeType.substring(mimeType.lastIndexOf("/") + 1);
            novoNomeArquivo = UUID.randomUUID() + "." + extensao;

            filePath = diretorio.resolve(novoNomeArquivo);

        } while (filePath.toFile().exists());

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        LOGGER.info("Arquivo salvo com sucesso: " + novoNomeArquivo);
        return filePath.toFile().getName();
    }

    @Override
    public File obter(String nomeArquivo) {
        File file = new File(PATH_USER + nomeArquivo);
        return file;
    }

    private void verificarTamanhoImagem(byte[] arquivo) throws IOException {
        if (arquivo.length > MAX_FILE_SIZE) {
            throw new IOException("Arquivo maior que 10mb");
        }
    }

    private void verificarTipoImagem(String nomeArquivo) throws IOException {
        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        if (mimeType == null || !SUPPORTED_MIME_TYPES.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportada ou não reconhecida.");
        }
    }
}
