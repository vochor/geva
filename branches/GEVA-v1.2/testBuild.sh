#!/bin/sh 
echo "Running testBuild.sh to compare output with facitOutput.dat"
cd bin
# Run files
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -generations 2 -main_class Main.Run -rng_seed 42 -output ../tmp0.dat > /dev/null
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.SantaFeAntTrail.SantaFeAntTrailBSF -grammar_file ../param/Grammar/sf_grammar_gr.bnf -generations 10 -population_size 50 -main_class Main.Run -rng_seed 42 -output ../tmp1.dat > /dev/null
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.ParityProblem.EvenFiveParityFitnessBSF -grammar_file ../param/Grammar/efp_grammar_gr.bnf -generations 10 -population_size 50 -main_class Main.Run -rng_seed 42 -output ../tmp2.dat > /dev/null
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.SymbolicRegression.SymbolicRegressionJScheme -grammar_file ../param/Grammar/sr_grammar_sch.bnf -generations 10 -population_size 50 -main_class Main.Run -rng_seed 42 -output ../tmp3.dat > /dev/null
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.SymbolicRegression.SymbolicRegressionBSF -grammar_file ../param/Grammar/sr_grammar_gr.bnf -generations 10 -population_size 50 -main_class Main.Run -rng_seed 42 -output ../tmp4.dat > /dev/null
java -jar GEVA.jar -properties_file ../param/Parameters/HelloWorld.properties -fitness_function FitnessEvaluation.HelloWorld.HelloWorldBSF -grammar_file ../param/Grammar/HelloWorld_grammar.bnf -generations 10 -population_size 50 -main_class Main.Run -rng_seed 42 -output ../tmp5.dat > /dev/null
cd ..
# Cut out time which will vary
cut -d " " -f 1-3,5-8 tmp*.dat > tmpCut.dat
# Check if the data is the same as in facitOutput.dat
diff facitOutput.dat tmpCut.dat > tmpDiff.dat
if [ -s tmpDiff.dat ]; then
# remove the files created
    echo "Files differ, check the tmp*dat files"
else
    echo "Removing temporary files"
    rm -v tmp*
fi