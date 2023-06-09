package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import constants.Configs;

public class FileService {

	private static boolean supressWarnings = false;

	public void run(String fileName, String data) throws Exception {
		File file = new File(makeFilePath(fileName));

		if (file.exists() && file.isFile()) {
			appendFile(file, data);
			return;
		}

		if (file.exists() && file.isDirectory()) {
			throw new Exception("O arquivo não pode ser um diretório");
		}

		createFile(file, data);
	}
	
	public Boolean fileExists(String fileName) {
		File file = new File(makeFilePath(fileName));

		if (file.exists() && file.isFile()) {
			return true;
		}
		
		return false;
	}
	
	private String makeFilePath(String fileName) {
		return String.format("%s" + "\\" + fileName + ".%s", Configs.dataDirectory, Configs.dataFileType);
	}

	private void createFile(File file, String data) throws IOException {
		FileWriter fwrite = new FileWriter(file, false);

		writeOnFile(fwrite, data);

		fwrite.close();
	}

	private void appendFile(File file, String data) throws IOException {
		FileWriter fwrite = new FileWriter(file, true);

		writeOnFile(fwrite, data);

		fwrite.close();
	}

	private void writeOnFile(FileWriter fwrite, String data) {
		PrintWriter print = new PrintWriter(fwrite);

		print.write(data);
		print.flush();
		print.close();
	}

	public String readData(String fileName) throws IOException {
		File file = new File(makeFilePath(fileName));

		if (file.exists() && file.isFile()) {
			FileInputStream fluxo = new FileInputStream(file);
			InputStreamReader leitor = new InputStreamReader(fluxo);

			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();
			StringBuffer text = new StringBuffer();

			while (linha != null) {
				text.append(linha + "\r\n");

				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();

			String finalText = text.toString();
			if (!supressWarnings && finalText.length() == 0) {
				JOptionPane.showMessageDialog(null, String.format("O arquivo '%s' está vazio", fileName));
			}

			return finalText;
		} else {
			if (!supressWarnings) {				
				JOptionPane.showMessageDialog(null, String.format("O arquivo '%s' não existe", fileName));
			}

			throw new IOException("Arquivo não existe");
		}
	}

	/**
	 * Seta a exibição da mensagem de arquivo vazio ou não existe
	 * 
	 * @param condition
	 */
	public void setSupressedWarning(boolean condition) {
		supressWarnings = condition;
	}
	
	public void updateLine(String fileName, String data, int line) throws IOException {
		File file = new File(makeFilePath(fileName));
		File tempFile =  new File(makeFilePath(fileName + "temp"));
		
		
		if (file.exists() && file.isFile()) {
			FileInputStream fluxo = new FileInputStream(file);
			InputStreamReader leitor = new InputStreamReader(fluxo);

			BufferedReader buffer = new BufferedReader(leitor);
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String linha = buffer.readLine();
			
			String toRead;
			int lineCount = 0;
			line--;
			while (linha != null) {
				
				if (lineCount == line) {
				    toRead = data;
				} else {
					toRead = linha + "\r\n";
				}
				
				writer.write(toRead);

				linha = buffer.readLine();
				
				lineCount++;
			}

			writer.close(); 
			buffer.close();
			leitor.close();
			fluxo.close();

			file.delete();
			tempFile.renameTo(file);
		} else {
			throw new IOException("Arquivo não existe");
		}
	}

}