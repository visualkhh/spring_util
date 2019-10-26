package com.omnicns.medicine.test.java;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicns.java.jackson.JacksonUtil;
import com.omnicns.medicine.domain.Adm;
import com.omnicns.medicine.domain.AdmAuth;

import java.io.IOException;
import java.util.Iterator;

public class JackSonTest {
	public static void main(String[] args) throws IOException {
		Adm adm = new Adm();
		adm.setAdmNm("gg");
		adm.setAdmSeq(1);
		AdmAuth a1 = new AdmAuth(); a1.setAuthId("a1");
		AdmAuth a2 = new AdmAuth(); a2.setAuthId("a2");
		AdmAuth a3 = new AdmAuth(); a3.setAuthId("a3");
//		adm.setAdmAuths(Arrays.asList(a1,a2,a3));




		String json = JacksonUtil.toJson(adm);
//		System.out.println(json);
//		Map<String, Object> r = JsonPath.read(json,"$.admNm");
//		System.out.println(r.toString());



		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json);
		//Coordinates coordinates = mapper.treeToValue(coordinatesNode, Coordinates.class);
		JsonNode coordinatesNode = node.at("/admAuths");
//		JsonNode coordinatesNode = node.path("/admAuths");
//		JsonNode coordinatesNode = node.at("/*");
//		JsonNode coordinatesNode = node.path("/admNm");

		Iterator<JsonNode> iterator = coordinatesNode.elements();
//		System.out.print("Marks: [ ");

		while (iterator.hasNext()) {
			JsonNode marks = iterator.next();
			System.out.println(marks);
		}


		System.out.println(coordinatesNode);


		// Make the object traversable by JSONPath.
//		Map<String, Object> mappedObject = mapper.convertValue(adm, Map.class);
//		System.out.println(mappedObject);
//		// Evaluate that expression
//		Object output = JsonPath.read(mappedObject, "$.admSeq");
//		System.out.println(output);


//		GsonUtil.




	}
}
