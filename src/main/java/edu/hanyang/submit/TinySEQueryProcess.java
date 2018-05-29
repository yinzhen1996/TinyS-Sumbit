package edu.hanyang.submit;

import java.io.IOException;

import edu.hanyang.indexer.DocumentCursor;
import edu.hanyang.indexer.PositionCursor;
import edu.hanyang.indexer.IntermediateList;
import edu.hanyang.indexer.IntermediatePositionalList;
import edu.hanyang.indexer.QueryPlanTree;

public class TinySEQueryProcess {
	public void op_and_wo_pos (DocumentCursor op1, DocumentCursor op2, IntermediateList out) throws IOException {
		// TODO: complete this
	}
	
	public void op_and_w_pos (DocumentCursor op1, DocumentCursor op2, int shift, IntermediatePositionalList out) throws IOException {
		// TODO: complete this
	}
	
	public QueryPlanTree parse_query(String query) throws IOException {
		// TODO: complete this
		return null;
	}
}
