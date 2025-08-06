#/bin/sh
ROOT=/p/POC/MT_POC
copiar() {
  for f in `find $ROOT -name "*.jar" -not -path "./bin/*"` ; do
       cp $f $ROOT/bin/`basename $f` 2> /dev/null;
   done
}

