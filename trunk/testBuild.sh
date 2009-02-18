#!/bin/sh 
cd bin
pwd
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -generations 2
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.SantaFeAntTrail.SantaFeAntTrailBSF -grammar_file ../param/Grammar/sf_grammar_gr.bnf -generations 10 -population_size 50
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.ParityProblem.EvenFiveParityFitnessBSF -grammar_file ../param/Grammar/efp_grammar_gr.bnf -generations 10 -population_size 50
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.SymbolicRegression.SymbolicRegressionJScheme -grammar_file ../param/Grammar/sr_grammar_sch.bnf -generations 10 -population_size 50
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.SymbolicRegression.SymbolicRegressionBSF -grammar_file ../param/Grammar/sr_grammar_gr.bnf -generations 10 -population_size 50
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.HelloWorld.HelloWorldBSF -grammar_file ../param/Grammar/HelloWorld_grammar.bnf -generations 10 -population_size 50
