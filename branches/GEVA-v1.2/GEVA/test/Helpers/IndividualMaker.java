package Helpers;

import Individuals.FitnessPackage.BasicFitness;
import Individuals.FitnessPackage.Fitness;
import Individuals.GEIndividual;
import Individuals.Genotype;
import Individuals.Phenotype;
import Mapper.GEGrammar;
import Mapper.Symbol;
import Util.Enums;
import java.util.Properties;

public class IndividualMaker {

    private IndividualMaker() {
    }

    public static GEIndividual makeIndividual() {
        Properties p = GrammarCreator.getProperties();
        GEGrammar geg = GrammarCreator.getGEGrammar(p);
        Phenotype phen = new Phenotype();
        Genotype geno = new Genotype();
        geno.add(GrammarCreator.getGEChromosome());
        Fitness f = new BasicFitness();
        GEIndividual gei = new GEIndividual(geg, phen, geno, f);
        return gei;
    }

    public static GEIndividual makeIndividual(Properties p) {
        GEGrammar geg = GrammarCreator.getGEGrammar(p);
        Phenotype phen = new Phenotype();
        Genotype geno = new Genotype();
        geno.add(GrammarCreator.getGEChromosome());
        Fitness f = new BasicFitness();
        GEIndividual gei = new GEIndividual(geg, phen, geno, f);
        return gei;
    }

    /**
     * Pass in string, split on whitspace. Create terminal symbol.
     * @param s String to be parsed to Phenotypes
     * @return
     */
    public static Phenotype getPhenotype(String s) {
        Phenotype p = new Phenotype();
        String[] splits = s.split("\\s+");
        for (String ss : splits) {
            if(!ss.equals("")) {
                p.add(new Symbol(ss, Enums.SymbolType.TSymbol));
            }
        }
        return p;
    }
}

