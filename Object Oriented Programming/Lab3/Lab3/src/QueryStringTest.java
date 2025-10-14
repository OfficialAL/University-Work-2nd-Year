public class QueryStringTest {
    public static void main(String[] args) {
        System.out.println("=== QUERY STRING UNIT TESTS ===\n");
        QueryStringTest tester = new QueryStringTest();
        tester.testGetParameter();
        tester.testHasParameter();
        tester.testGetParameterOffset();
        tester.testDecode();
        tester.testEdgeCases();
        tester.testSpecificQueryStrings();
        tester.testEncodedValues();
        System.out.println("=== ALL TESTS COMPLETED ===");
    }

    public void testGetParameter() {
        System.out.println("TEST: getParameter()");
        QueryString qs = new QueryString("name=Alice&age=19&country=UK");
        boolean pass1 = "Alice".equals(qs.getParameter("name"));
        boolean pass2 = "19".equals(qs.getParameter("age"));
        boolean pass3 = "UK".equals(qs.getParameter("country"));
        boolean pass4 = (qs.getParameter("missing") == null);
        System.out.println("name=Alice: " + pass1);
        System.out.println("age=19: " + pass2);
        System.out.println("country=UK: " + pass3);
        System.out.println("missing=null: " + pass4);
        System.out.println("PASS: " + (pass1 && pass2 && pass3 && pass4) + "\n");
    }

    public void testHasParameter() {
        System.out.println("TEST: hasParameter()");
        QueryString qs = new QueryString("name=Alice&age=19&country=UK");
        boolean pass1 = qs.hasParameter("age");
        boolean pass2 = !qs.hasParameter("missing");
        System.out.println("has age: " + pass1);
        System.out.println("has missing: " + pass2);
        System.out.println("PASS: " + (pass1 && pass2) + "\n");
    }

    public void testGetParameterOffset() {
        System.out.println("TEST: getParameterOffset()");
        QueryString qs = new QueryString("name=Alice&age=19&country=UK");
        int offsetName = qs.getParameterOffset("name");
        int offsetCountry = qs.getParameterOffset("country");
        int offsetMissing = qs.getParameterOffset("missing");
        boolean pass1 = (offsetName == 0);
        boolean pass2 = (offsetCountry == 18); // 'country' starts after 'name=Alice&age=19&'
        boolean pass3 = (offsetMissing == -1);
        System.out.println("offset name: " + offsetName);
        System.out.println("offset country: " + offsetCountry);
        System.out.println("offset missing: " + offsetMissing);
        System.out.println("PASS: " + (pass1 && pass2 && pass3) + "\n");
    }

    public void testDecode() {
        System.out.println("TEST: decode()");
        String encoded = "Hello%2C+world%21";
        String decoded = QueryString.decode(encoded);
        boolean pass = "Hello, world!".equals(decoded);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decoded);
        System.out.println("PASS: " + pass + "\n");
    }

    public void testEdgeCases() {
        System.out.println("TEST: Edge Cases");
        QueryString empty = new QueryString("");
        boolean pass1 = (empty.getParameter("any") == null);
        boolean pass2 = !empty.hasParameter("any");
        boolean pass3 = (empty.getParameterOffset("any") == -1);
        QueryString repeated = new QueryString("name=Alice&name=Bob");
        boolean pass4 = "Alice".equals(repeated.getParameter("name")); // Should return first occurrence
        QueryString encoded = new QueryString("message=Hello%2C+world%21");
        boolean pass5 = "Hello, world!".equals(QueryString.decode(encoded.getParameter("message")));
        System.out.println("empty getParameter: " + pass1);
        System.out.println("empty hasParameter: " + pass2);
        System.out.println("empty getParameterOffset: " + pass3);
        System.out.println("repeated name=Alice: " + pass4);
        System.out.println("encoded message: " + pass5);
        System.out.println("PASS: " + (pass1 && pass2 && pass3 && pass4 && pass5) + "\n");
    }

    public void testSpecificQueryStrings() {
        System.out.println("TEST: Specific Required Query Strings");
        
        // Test the exact query strings specified in requirements
        QueryString qs1 = new QueryString("name=Alice&age=19&country=UK");
        QueryString qs2 = new QueryString("message=Hello%2C+world%21");
        
        // Test all parameters in first query string
        boolean pass1 = "Alice".equals(qs1.getParameter("name"));
        boolean pass2 = "19".equals(qs1.getParameter("age"));
        boolean pass3 = "UK".equals(qs1.getParameter("country"));
        boolean pass4 = qs1.hasParameter("name") && qs1.hasParameter("age") && qs1.hasParameter("country");
        boolean pass5 = !qs1.hasParameter("nonexistent");
        
        // Test message query string
        boolean pass6 = "Hello, world!".equals(qs2.getParameter("message")); // getParameter should return decoded
        boolean pass7 = "Hello, world!".equals(QueryString.decode("Hello%2C+world%21")); // Test decode directly
        
        System.out.println("name=Alice: " + pass1);
        System.out.println("age=19: " + pass2);
        System.out.println("country=UK: " + pass3);
        System.out.println("has all parameters: " + pass4);
        System.out.println("doesn't have nonexistent: " + pass5);
        System.out.println("message decoded from getParameter: " + pass6);
        System.out.println("decode method test: " + pass7);
        System.out.println("PASS: " + (pass1 && pass2 && pass3 && pass4 && pass5 && pass6 && pass7) + "\n");
    }

    public void testEncodedValues() {
        System.out.println("TEST: Various Encoded Values");
        
        // Test different encoding scenarios
        boolean pass1 = "Hello, world!".equals(QueryString.decode("Hello%2C+world%21"));
        boolean pass2 = "test@example.com".equals(QueryString.decode("test%40example.com"));
        boolean pass3 = "space test".equals(QueryString.decode("space+test"));
        boolean pass4 = "100%".equals(QueryString.decode("100%25"));
        boolean pass5 = (QueryString.decode(null) == null);
        
        System.out.println("Hello%2C+world%21 -> Hello, world!: " + pass1);
        System.out.println("test%40example.com -> test@example.com: " + pass2);
        System.out.println("space+test -> space test: " + pass3);
        System.out.println("100%25 -> 100%: " + pass4);
        System.out.println("null -> null: " + pass5);
        System.out.println("PASS: " + (pass1 && pass2 && pass3 && pass4 && pass5) + "\n");
    }
}
