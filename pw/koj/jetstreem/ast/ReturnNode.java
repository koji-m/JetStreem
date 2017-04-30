package pw.koj.jetstreem.ast;

import pw.koj.jetstreem.compiler.*;

public class ReturnNode extends Node {
    protected ArrayNode args;

    public ReturnNode() {}

    public ReturnNode(ArrayNode args) {
        super();
        this.args = args;
    }

    public ArrayNode getArgs() {
        return args;
    }

    public void setArgs(ArrayNode args) {
        this.args = args;
    }

    public Location location() {
        return null;
    }

    public void _dump(Dumper d) {
      //TBD
    }
    
    public Object accept(Visitor visitor) throws CompileError {
        return visitor.visit(this);
    }
}

