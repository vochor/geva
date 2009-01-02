package Mapper;

import Util.Structures.TreeNode;

/**
 *
 * @author eliott bartley
 */
public class DerivationNode extends TreeNode<Symbol>
{

    private int codonIndex = -1;
    /*private int codonValue = 0;*/
    private int codonPick = -1;

    public DerivationNode() { }
    public DerivationNode(TreeNode<Symbol> copy) { super(copy); }
    public DerivationNode(TreeNode<Symbol> parent, Symbol data)
    {   super(parent, data);
    }

    void setCodonIndex(int codonIndex/*, int codonValue*/, int codonPick)
    {   this.codonIndex = codonIndex;
        /*this.codonValue = codonValue;*/
        this.codonPick = codonPick;
    }

    /**
     * Get the index of the codon used to choose the child (production) of this
     *  tree node. -1 is returned if no codon was used (e.g., if production was
     *  choosen because it was the only one to pick)
     */
    public int getCodonIndex()
    {   return codonIndex;
    }

    /**
     * Get the production picked by the codon. -1 is returned if no codon was
     *  used (e.g., if production was choosen because it was the only one to
     *  pick)
     */
    public int getCodonPick()
    {   return codonPick;
    }
    
    /**
     * Build the string using a textural tree-view
     * @return String
     */
    @Override
    public String toString()
    {   StringBuilder builder = new StringBuilder();
        toString(builder, "");
        return builder.toString();
    }

    /**
     * Recursively build the string using a textual tree-view. Each recursion
     *  passes an <var>indent</var> to append to the start of each line. If
     *  indent contains the value "\1", it is output as "|_" and then replaced
     *  with the value "| ", if indent contains the value "\2", it is output as
     *  "|_" and then replaced with "  ". Therefore, each parent that has
     *  siblings following it should add "\1" to the indent, and each parent
     *  that has no following siblings add "\2", this way, the branch for
     *  parents with following siblings (for all grand-children) will output
     *  "| " indicating more to follow, where parents with no following siblings
     *  would output "  " indicating no more to follow
     * @param builder The string builder to append the string
     * @param indent The string used to start each line
     * @return true if a new-line was added after the last entry, else false
     */
    private boolean toString(StringBuilder builder, String indent)
    {

        builder.append(indent.replaceAll("\1", "|_").replaceAll("\2", "|_"));
        builder.append(super.getData());
        if(codonIndex != -1)
        {   builder.append(" (");
            builder.append(codonIndex);
            /*builder.append(":");
            builder.append(codonValue);
            builder.append(":");
            builder.append(codonChose);
            */
            builder.append(")");
        }
        if(super.size() != 0)
        {   builder.append("\n");
            for(int i = 0; i < super.size(); i++)
                if(((DerivationNode)super.get(i)).toString
                (   builder,
                    indent.replaceAll("\1", "| ").replaceAll("\2", "  ")
                  + (i < super.size() - 1 ? "\1" : "\2")
                ) == false)
                    if(i < super.size() - 1)
                        builder.append("\n");
                    else
                        return false;
            return true;
        }
        return false;
            
    }
    
}
