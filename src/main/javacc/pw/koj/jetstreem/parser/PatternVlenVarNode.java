package pw.koj.jetstreem.parser;

import pw.koj.jetstreem.compiler.*;
import pw.koj.jetstreem.compiler.ir.*;

public class PatternVlenVarNode extends Node {
    private String name;

    public PatternVlenVarNode() {
        super();
    }

    public PatternVlenVarNode(IdentifierNode id) {
        this.name = id.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location location() {
        return null;
    }

    public IrNode accept(Visitor visitor) throws CompileError {
        return visitor.visit(this);
    }       
}

