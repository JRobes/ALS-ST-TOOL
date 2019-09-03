# ALS-ST-TOOL
This small library provides methods to extract and order the selections made with MSC Patran sofrware or any Nastran Sets.

Nastran is a finite element analysis (FEA) software and Patran is computed aided negineering software (CAE) used to pre-process and post-process of Finite Element Models (FEM) such as the models to be analyzed with Nastran.
As a usual workflow, the users create a finite element model with Patran, then analyze it with Nastran and post-process the results with Patran again

#### Nastran Sets format
NASTRAN is a finite element analysis (FEA)
Sets of elements or nodes are formated in Nastran with comma sepparate values. In case consecutive numbers the word THRU is used between mayor and minor number of the series.

The following is an example of one Set

    $ Elements from Group: RIB14-15-16R 
    SET 5 =  54151001 THRU 54151007, 54151101 THRU 54151107, 54151500,
    54151515, 54151555, 54151575, 54151600, 54151615
    
    $ Nodes from Group: RIB14-15-16R 
    SET 6 =  5452000 THRU 5452007, 5452015, 5452020 THRU 5452027, 5452035,
    5452080 THRU 5452087, 5452095, 5452100 THRU 5452107, 5452115

#### Patran select format
