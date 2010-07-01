package Operator.Operations;

import Individuals.Individual;
import Util.Random.MersenneTwisterFast;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author eliott barley
 */
public class SimpleUserSelect extends UserSelect
{

    private ArrayList<JCheckBox> checks;

    @Override
    public void userPick(List<Individual> operands)
    {   super.userPick(operands);
    }

    @Override
    protected void display(List<Individual> operands)
    {   int row = 1;
        checks = new ArrayList<JCheckBox>(operands.size());

        super.enableDone(true);

        getPanel().setLayout(new GridBagLayout());

        gridAdd(getPanel(), new JLabel("Pick"), 0, 0, 0);
        gridAdd(getPanel(), new JLabel("Phenotype"), 1, 0, 0);

        for(Individual operand : operands)
        {

            JCheckBox chkPick = new JCheckBox();
            JTextField txtPheno = new JTextField();
            txtPheno.setText(operand.getPhenotype().getString());

            gridAdd(getPanel(), chkPick, 0, row, 0);
            gridAdd(getPanel(), txtPheno, 1, row, 0);

            checks.add(chkPick);
            
            row++;

        }

        super.display(operands);
        
    }

    protected void select(List<Individual> operands)
    {
        for(int index = 0; index < operands.size(); index++)
            if(checks.get(index).isSelected() == true)
                super.selectedPopulation.add(operands.get(index).clone());
    }

}
