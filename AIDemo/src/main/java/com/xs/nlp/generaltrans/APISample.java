package com.xs.nlp.generaltrans;

public class APISample {
	public static void main(String[] args) throws Exception {
		TransUtil transUtil = new TransUtil("APPID","密钥");
		String query = "<image> <h1>";
		System.out.println(asciiToNative(transUtil.getTransResult(query, "auto", "zh")));
	}

	private static String asciiToNative(String asciicode) {
		String[] asciis = asciicode.split("\\\\u");
		String nativeValue = asciis[0];
		try {
			for (int i = 1; i < asciis.length; i++) {
				String code = asciis[i];
				nativeValue += (char) Integer
						.parseInt(code.substring(0, 4), 16);
				if (code.length() > 4) {
					nativeValue += code.substring(4, code.length());
				}
			}
		} catch (NumberFormatException e) {
			return asciicode;
		}
		return nativeValue;
	}
}
