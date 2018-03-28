package edu.hanyang;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang3.tuple.MutableTriple;
import org.junit.Test;

import edu.hanyang.submit.TinySEExternalSort;

public class ExternalSortTest {
	@Test
	public void sampleDataTest() throws IOException {
		TinySEExternalSort sort = new TinySEExternalSort();
		int blocksize = 1024;
		int nblocks = 10;
		String infile = "./data/test-100000.data";
		String outfile = "./result";
		String tmpdir = "./tmp";
		sort.sort(infile, outfile, tmpdir, blocksize, nblocks);

		// In memory sort
		DataInputStream reader = new DataInputStream(new BufferedInputStream(new FileInputStream(infile)));
		ArrayList<MutableTriple<Integer, Integer, Integer>> arr = new ArrayList<>();
		try {
			while (true) {
				arr.add(new MutableTriple<Integer, Integer, Integer>(reader.readInt(), reader.readInt(), reader.readInt()));
			}
		} catch (IOException e) {
		}
		Collections.sort(arr);
		reader.close();

		// External sort
		File file = new File("./result");
		File[] files = file.listFiles();
		assertEquals(files.length, 1);
		
		DataInputStream reader2 = new DataInputStream(new BufferedInputStream(new FileInputStream(files[0].getAbsoluteFile())));
		ArrayList<MutableTriple<Integer, Integer, Integer>> arr2 = new ArrayList<>();
		try {
			while (true) {
				arr2.add(new MutableTriple(reader2.readInt(), reader2.readInt(), reader2.readInt()));
			}
		} catch (IOException e) {
		}

		// Compare
		assertEquals(arr.size(), arr2.size());
		for (int i = 0; i < arr2.size(); i++) {
			assertEquals(arr.get(i), arr2.get(i));
		}
	}

	@Test
	public void bigdataTest() throws IOException {
		TinySEExternalSort sort = new TinySEExternalSort();
		int blocksize = 8192;
		int nblocks = 10;
		String infile = "./data/test-100000000.data";
		String outfile = "./result";
		String tmpdir = "./tmp";
		
		do {
			try {
				sort.sort(infile, outfile, tmpdir, blocksize, nblocks);
			}
			catch (OutOfMemoryError e) {
				nblocks /= 2;
			}
		}
		while (nblocks > 0);
	}

}
