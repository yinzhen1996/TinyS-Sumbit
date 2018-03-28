package edu.hanyang;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.hanyang.submit.TinySEExternalSort;

public class ExternalSortTest {
	@Before
	public void init() {
		clean("./tmp");
		File resultFile = new File("./sorted.data");
		if(resultFile.exists()) {
			resultFile.delete();
		}
	}
	
	@Test
	public void TestSort() throws IOException {
		int blocksize = 1024;
		int nblocks = 10;
		ClassLoader classLoader = getClass().getClassLoader();
		File infile = new File(classLoader.getResource("test.data").getFile());
		String outfile = "./sorted.data";
		String tmpdir = "./tmp";
		File resultFile = new File(outfile);
		
		TinySEExternalSort sort = new TinySEExternalSort();
		sort.sort(infile.getAbsolutePath(), outfile, tmpdir, blocksize, nblocks);

		
		File answerFile = new File(classLoader.getResource("answer.data").getFile());
		DataInputStream resultInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(resultFile)));
		DataInputStream answerInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(answerFile)));

		assertNotNull(resultInputStream);
		assertNotNull(answerInputStream);

		for (int i = 0; i < 100000; i++) {
			assertEquals(resultInputStream.readInt(), answerInputStream.readInt());
			assertEquals(resultInputStream.readInt(), answerInputStream.readInt());
			assertEquals(resultInputStream.readInt(), answerInputStream.readInt());
		}

		resultInputStream.close();
		answerInputStream.close();
	}

	private void clean(String dir) {
		File file = new File(dir);
		File[] tmpFiles = file.listFiles();
		if (tmpFiles != null) {
			for (int i = 0; i < tmpFiles.length; i++) {
				if (tmpFiles[i].isFile()) {
					tmpFiles[i].delete();
				} else {
					clean(tmpFiles[i].getAbsolutePath());
				}
				tmpFiles[i].delete();
			}
			file.delete();
		}
	}
}
