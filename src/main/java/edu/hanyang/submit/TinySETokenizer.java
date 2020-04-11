package edu.hanyang.submit;

import java.util.List;

import edu.hanyang.indexer.Tokenizer;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.tartarus.snowball.ext.PorterStemmer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class TinySETokenizer implements Tokenizer {

	SimpleAnalyzer analyzer;
	PorterStemmer stemmer;
	List<String> output = new ArrayList<String>();
	
	public void setup() {
		analyzer = new SimpleAnalyzer();
		stemmer = new PorterStemmer();
	}

	public List<String> split(String text) {
		try {
			TokenStream tokenStream = analyzer.tokenStream("test", text);
			tokenStream.reset();
			CharTermAttribute termAtt = tokenStream.getAttribute(CharTermAttribute.class);
			while (tokenStream.incrementToken()) {
				String temp = termAtt.toString();
				stemmer.setCurrent(temp);
				stemmer.stem();
				String input = stemmer.getCurrent();
				output.add(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void clean() {
		output.clear();
	}

}
