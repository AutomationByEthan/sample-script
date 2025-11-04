# Free Selenium Login Validation – Runs in 60 Seconds

[![Watch 60-sec run](https://img.youtube.com/vi/YA9p_Kg1xzw/0.jpg)](https://www.youtube.com/watch?v=YA9p_Kg1xzw)

> **No Maven. No ChromeDriver. No Admin Rights.**  
> Just **3–4 commands** — works on **Windows, Mac, Linux** *(JDK 17 required)*.

---

## Run in 60 Seconds (open from your command line)

```bash
git clone https://github.com/automationbyethan/sample-script
cd sample-script

### Windows
```cmd
git clone https://github.com/automationbyethan/sample-script
cd sample-script
set "JAVA_HOME=C:\Program Files\Java\jdk-17"
mvnw.cmd test

# Mac / Linux
git clone https://github.com/automationbyethan/sample-script
cd sample-script
export JAVA_HOME=$(/usr/libexec/java_home -v 17 2>/dev/null || echo "/path/to/jdk-17")
./mvnw test

Need JDK 17?
Download free in 2 minutes → adoptium.net
