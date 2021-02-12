package com.zz.dsl.graalvm;

import com.oracle.truffle.api.TruffleLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class State {
    CalcLanguage language;
    TruffleLanguage.Env env;
    int v;
}
