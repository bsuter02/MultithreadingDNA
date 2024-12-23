# Project Purpose

The purpose of this project is to create a multithreaded program to isolate Open Reading Frames (ORFs) from DNA sequences.

## Explaining each Filter

* Filter 1 is dedicated to the removal of delimiters from the DNA sequences provided. The delimiters are noted in the sequence as 'N'
* Filter 2 generate the reverse complements of the provided sequences from Filter 1 such that A <-> T and C <-> G
* Filter 3 produces shifted versions of DNA strands provided by Filter 2 for different reading frames
* Filter 4 converts the DNA strands from Filter 3 to RNA strands and then to amino acid squences
* Filter 5 indetifies whether any output of Filter 4 is an Open Reading Frame

## Important Notes

This project was completed in Autumn of 2022.
Example inputs are provided within the Driver class.
