library(stats)
library(effsize)

# Input parameters
alpha <- 0.05
dir <- "data"
outdir <- "res"
files <- list.files(dir)
dir.create(outdir,recursive=TRUE)

for(f in files){
  id <- substring(f,0,nchar(f)-4) # Remove .csv
  filename <- paste(dir,"/",f,sep="")
  filename
  outfile <- paste(outdir,"/test_",id,".txt",sep="")
  
  # Open dataset
  data <- read.csv(filename,header=TRUE)
  
  # Start writing some information in the output file
  sink(outfile)
  writeLines("INFORMATION")
  writeLines(paste("Input file:", filename))
  writeLines(paste("Alpha=",alpha))
  writeLines("----------------")
  
  # Pairwise comparisons (Wilcoxon)
  writeLines("PAIRWISE COMPARISON (WILCOXON)")
  wTest <- wilcox.test(data[,1], data[,2], conf.level=(1-alpha), exact=FALSE)
  print(wTest)
  writeLines("\n")
  pValueWilcox <- wTest$p.value
  result <- "INTERPRETATION: The Null hypothesis "
  if(pValueWilcox < alpha){
    result <- paste(result, "can be rejected (", pValueWilcox,"<",alpha,")",sep="")
  }else{
    result <- paste(result, "cannot be rejected", sep="")
  }
  writeLines(result)
  
  # Effect size
  writeLines("\n")
  writeLines("EFFECT SIZE (CLIFF'S DELTA)\n")
  cTest <- suppressWarnings(cliff.delta(data[,1], data[,2], conf.level=(1-alpha)))
  print(cTest)
  writeLines("\n")

  # Close output device
  sink()
}
