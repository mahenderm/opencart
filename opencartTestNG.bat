set pl=D:\luna\Practise\opencartTest
D:
cd %pl%
set classpath=%pl%\bin;%pl%\lib\*
java org.testng.TestNG %pl%\testng.xml
pause