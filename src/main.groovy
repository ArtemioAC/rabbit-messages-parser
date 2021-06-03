import groovy.io.FileType

def pattern = 'Sending message'
def dir = new File("../src/resources/")

dir.eachFileRecurse (FileType.FILES) { file ->
    println "Found file: ${file.name}"

    def outputFile = new File("../src/output/", "Messages_${file.name}")
    outputFile.write("")

    def messagesNumber = 0
    file.eachLine {line ->
        if (line.contains(pattern)){
            def message = line.split("\\{")[1].split("}")[0]
            //println "{${message}}"
            outputFile.append("{${message}}\n")
            messagesNumber++
        }
    }
    outputFile.append("Found ${messagesNumber} messages\n")
}

