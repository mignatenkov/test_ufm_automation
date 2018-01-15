mkdir ufm_dev
cd ufm_dev
git clone https://github.com/mignatenkov/test_ufm.git
cd test_ufm
mvn package
cd ..
mv test_ufm/target/test_ufm.jar ./
java -jar test_ufm.jar