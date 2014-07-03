package ch.zuehlke.camp.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

@Named
public class CsvReader extends AbstractItemReader {

	private Collection<Pair<String, BufferedReader>> sources;
	/*private String[] sourceFiles = { "CONV_FAC.csv", "FOOD_GRP.csv", "FOOD_NM.csv",
			"FOOD_SRC.csv", "MEASURE.csv", "NT_AMT.csv", "NT_NM.csv",
			"NT_SRC.csv", "REFU_NM.csv", "REFUSE.csv", "YIELD.csv",
			"YLD_NM.csv" };*/
	private String[] sourceFiles = { "FOOD_GRP.csv", "FOOD_SRC.csv", "FOOD_NM.csv", "NT_SRC.csv", "NT_NM.csv", "NT_AMT.csv" };


	@Override
	public void open(Serializable checkpoint) throws Exception {
		sources = new ArrayList<Pair<String, BufferedReader>>();

		for (String src : sourceFiles) {
			String path = "/META-INF/" + src;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					this.getClass().getClassLoader().getResourceAsStream(path)));

			sources.add(new Pair<String, BufferedReader>(src, reader));
		}

	}

	@Override
	public Pair<String, String> readItem() {
		try {
			for (Pair<String, BufferedReader> src : sources) {
				String file = src.getFirst();
				BufferedReader reader = src.getSecond();
				
				String line;
				if ((line = reader.readLine()) != null) {
					return new Pair<String, String>(file,line);
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(CsvReader.class.getName()).log(Level.SEVERE, "Error while reading CSV Files!",
					ex);
		}
		return null;
	}
}
