package com.sdp.base.parameters;

import com.sdp.base.exceptions.InvalidArgumentException;
import com.sdp.base.logging.loggers.CLogger;
import com.sdp.sal.mask.RC;

import java.util.*;

public class CLP {
    static final String badParm  = "Parametro invalido: %s";
    static final String badValue = "El valor para el parametro ";
    static final String invalid  = "%s '%s' no es valido '%s'";

    static Props props = new Props();

    public static Props parseParms(String[] args, Map<String, CLPParameter> options)  {
        props = new Props();
        Map<String, CLPParameter> opt = addDefaults(options);
        try {
            for (int i = 0; i < args.length; i++) {
                if (!args[i].startsWith("-")) throw new InvalidArgumentException(badParm, args[i]);
                if (args[i].substring(0, 2).compareTo("--") == 0) {
                    i = processParameter(i, args, options);
                } else {
                    i = processSwitch(i, args, options);
                }
            }
        } catch (InvalidArgumentException ex) {
            CLogger.error(ex.getLocalizedMessage());
            System.exit(RC.ERROR);
        }
        return props;
    }
    private static int processParameter(int idx, String[] args, Map<String, CLPParameter> options) throws InvalidArgumentException {
            String parm = args[idx].substring(2);

            // Control de ayuda
            if (parm.compareToIgnoreCase("HELP") == 0) {
                props.put("help", 1);
                return args.length;
            }

            CLPParameter option = options.get(parm);
            if (option == null) throw new InvalidArgumentException(badParm, args[idx]);

            // Control de argumento valido
            return parseParm(++idx, args, option, props);
    }
    private static int processSwitch(int idx, String[] args, Map<String, CLPParameter> options) throws InvalidArgumentException {
        String parm = args[idx].substring(1);

        // Control de ayuda
        if (parm.compareToIgnoreCase("H") == 0) {
            props.put("help", 1);
            return args.length;
        }

        CLPParameter option = options.get(parm);
        if (option == null) throw new InvalidArgumentException(badParm, args[idx]);

        props.put(option.name, 1);
        return idx;
    }
    private static int parseParm(int idx, String[] args, CLPParameter parm, Properties props) throws InvalidArgumentException {
        if (idx >= args.length) throw new InvalidArgumentException("Falta el valor para el parametro: %s" , args[idx-1]);
        switch (parm.type) {
            case INT:    props.put(parm.name, checkInt(args, idx)); break;
            case PINT:   props.put(parm.name, checkPInt(args, idx)); break;
            case NINT:   props.put(parm.name, checkNInt(args, idx)); break;
            case STRING: props.put(parm.name, args[idx]); break;
        }
        return idx;
    }
    private static String checkInt(String[] args, int idx) throws InvalidArgumentException {
        return checkIntValue(args,idx).toString();
    }
    private static String checkPInt(String[] args, int idx) throws InvalidArgumentException {
        Integer value = checkIntValue(args,idx);
        if (value < 1) throw new InvalidArgumentException(invalid, badValue, args[idx-1], args[idx]);
        return value.toString();
    }
    private static String checkNInt(String[] args, int idx) throws InvalidArgumentException {
        Integer value = checkIntValue(args,idx);
        if (value < 0) throw new InvalidArgumentException(invalid, badValue, args[idx-1], args[idx]);
        return value.toString();
    }
    private static Integer checkIntValue(String[] args, int idx) throws InvalidArgumentException {
        try {
            return Integer.parseInt(args[idx]);
        } catch (NumberFormatException ex) {
            throw new InvalidArgumentException("%s '%s' no es entero '%s'", badValue, args[idx-1], args[idx]);
        }
    }
    private static Map<String, CLPParameter> addDefaults(Map<String, CLPParameter> options) {
        Map<String, CLPParameter> opt = new HashMap<String, CLPParameter>();
        opt.putAll(options);
        options.put("log",   new CLPParameter("log",   CLPType.NINT));
        options.put("debug", new CLPParameter("debug", CLPType.NINT));
        options.put("info",  new CLPParameter("info",  CLPType.NINT));
        return opt;
    }
}
