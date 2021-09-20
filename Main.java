package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        DirectoryStream.Filter<Path> filter = p -> Files.isRegularFile(p);

        
// 		The code on line 16 is hardcoded for unix and linux machines:
//      Path directory = FileSystems.getDefault().getPath("FileTree/Dir2");  
	    
// 		The code on line 19 is hardcoded for Windows machines: 	    
//	Path directory = FileSystems.getDefault().getPath("FileTree\\Dir2");   

// 		The code on line 22 is hardcoded for all machines
	Path directory = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir2");  
	    try(DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
	        for(Path file : contents) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException e) {
            System.out.println(e.getMessage());
        }

	    String separator = File.separator;
        System.out.println(separator);
        separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);

        try {
            Path tempFile = Files.createTempFile("myapp", ".appext");
            System.out.println("Temporary file path = " + tempFile.toAbsolutePath());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for(FileStore store : stores) {
            System.out.println("Volume name/Drive letter = "+ store);
            System.out.println("file store = " + store.name());
        }

        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for(Path path : rootPaths) {
            System.out.println(path);
        }
    }
}
