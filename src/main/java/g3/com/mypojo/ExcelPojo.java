package g3.com.mypojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ExcelPojo {
	@JsonProperty("Guilde")
	private List<List<String>> guilde;

	@JsonProperty("Event Code")
	private List<List<String>> eventCode;

	@JsonProperty("Data List")
	private List<List<String>> canList;

	@JsonProperty("School Code")
	private List<List<String>> schoolCodeList;

	@JsonProperty("Param")
	private List<List<String>> campusProgramList;

	public ExcelPojo() {
		super();
	}

	public List<List<String>> getData() {
		List<List<String>> data = null;
		if (guilde != null) {
			data = guilde;
		} else if (eventCode != null) {
			data = eventCode;
		} else if (canList != null) {
			data = canList;
		} else if (schoolCodeList != null) {
			data = schoolCodeList;
		} else if (campusProgramList != null) {
			data = campusProgramList;
		}
		return data;
	}
}