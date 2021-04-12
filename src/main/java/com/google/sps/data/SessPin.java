// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.data;
//import java.util.HashMap; 

/** An item on a todo list. */
public final class SessPin {


  private final int sessPin;
  private final int quizzId;
  private final long timestamp;
  //HashMap<Integer, Integer> currentSessions = new HashMap<Integer, Integer>();

  public SessPin(int sessPin, int quizzId, long timestamp) {
    this.sessPin = sessPin;
    this.quizzId = quizzId;
    this.timestamp = timestamp;
    //currentSessions.put(quizzId, sessPin);
    //System.out.println("hashMap: " + currentSessions );
  }

  
}




