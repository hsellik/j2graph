package com.github.sergdelft.j2graph;

import com.github.sergdelft.j2graph.builder.MethodGraphBuilder;
import com.github.sergdelft.j2graph.builder.NonTerminalBuilder;
import com.github.sergdelft.j2graph.output.dot.DotGenerator;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

public class MethodGraphBuilderTest {

    @Test
    void typilus_paper_example() {
        MethodGraphBuilder builder = new MethodGraphBuilder("m1");

        NonTerminalBuilder nt1 = builder.root("ASSIGN");
        Pair<Symbol, Token> foo = nt1.symbol("foo");
        nt1.token("=");

        NonTerminalBuilder nt2 = nt1.nonTerminal("CALL");
        nt2.token("get_foo", true);
        nt2.token("(");
        foo.getRight().assignedFrom(nt2.getNode());

        NonTerminalBuilder nt3 = nt2.nonTerminal("ARGUMENT");
        nt3.symbol("i");
        nt3.token(",");

        NonTerminalBuilder nt4 = nt3.nonTerminal("BINOP");
        nt4.symbol("i");
        nt4.token("+");
        nt4.token("1");

        nt2.token(")");

        MethodGraph graph = builder.build();

        String dot = new DotGenerator().generate(graph);
        System.out.println(dot);

    }
}