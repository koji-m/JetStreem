package pw.koj.jetstreem.parser;

import pw.koj.jetstreem.compiler.*;
import pw.koj.jetstreem.compiler.ir.*;

public class FunCallNode extends ExprNode {
    protected Location location;
    protected IdentifierNode id;
    protected ArrayNode args;

    public FunCallNode() {}

    public FunCallNode(Location loc, IdentifierNode id, ArrayNode args, Node blk) {
        super();
        this.location = loc;
        this.id = id;
        if (args == null) { args = new ArrayNode(loc); }
        if (blk != null) { args.add(blk); }
        this.args = args;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public IdentifierNode getId() {
        return id;
    }

    public void setId(IdentifierNode id) {
        this.id = id;
    }

    public ArrayNode getArgs() {
        return args;
    }

    public void setArgs(ArrayNode args) {
        this.args = args;
    }

    protected void _dump(Dumper d) {
        // TBD
    }

    public Location location() {
        return location;
    }

    public IrNode accept(Visitor visitor) throws CompileError {
        return visitor.visit(this);
    }       
}

