// CleanOTAInterface.aidl
package com.sjy.aidlserver;
import com.sjy.aidlserver.CleanCallback;

// Declare any non-default types here with import statements

interface CleanOTAInterface {

    void cleanOTACache(CleanCallback callback);
}