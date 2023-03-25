ublic static void main(String[] args) {
    if (args.length == 0) {
        System.out.println("Usage: java PitiInterpreter <filename>");
        System.exit(1);
    }

    try {
        String input = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
        PitiLexer lexer = new PitiLexer(CharStreams.fromString(input));
        PitiParser parser = new PitiParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.program();

        PitiInterpreter interpreter = new PitiInterpreter();
        interpreter.visit(tree);
    } catch (IOException e) {
        System.err.println("Error: " + e.getMessage());
        System.exit(1);
    }
}
