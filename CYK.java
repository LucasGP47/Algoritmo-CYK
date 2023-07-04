package cyk;

import java.util.*;

public class CYK {
	
	    public static boolean parse(Map<String, List<String>> alfabeto, String cad) {
	    	
	        int n = cad.length();
	        int r = alfabeto.size();
	        Set<String>[][] P = new Set[n][n];

	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                P[i][j] = new HashSet<>();
	            }
	        }

	        for (int i = 0; i < n; i++) {
	            for (String regra : alfabeto.keySet()) {
	                for (String producao : alfabeto.get(regra)) {
	                    if (cad.charAt(i) == producao.charAt(0)) {
	                        P[i][i].add(regra);
	                    }
	                }
	            }
	        }

	        for (int l = 1; l < n; l++) {
	            for (int i = 0; i < n - l; i++) {
	                int j = i + l;
	                for (int k = i; k < j; k++) {
	                    for (String regra : alfabeto.keySet()) {
	                        for (String producao : alfabeto.get(regra)) {
	                            if (producao.length() == 2) {
	                                String B = producao.substring(0, 1);
	                                String C = producao.substring(1);
	                                if (P[i][k].contains(B) && P[k + 1][j].contains(C)) {
	                                    P[i][j].add(regra);
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        }

	        return P[0][n - 1].contains("S");
	    }

	    public static void main(String[] args) {

	        Map<String, List<String>> alfabeto = new HashMap<>();
	        alfabeto.put("S", Arrays.asList("AB", "XB", "XS")); 
	        alfabeto.put("A", Arrays.asList("a"));
	        alfabeto.put("B", Arrays.asList("b"));
	        alfabeto.put("X", Arrays.asList("AS"));

	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Digite uma cad: ");
	        String cad = scanner.nextLine();
	        boolean pertence = parse(alfabeto, cad);
	        System.out.println(pertence ? "Linguagem aceita!" : "Linguagem não aceita!");
	        scanner.close();
	    }
	}

