// Candidate.java
package org.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Candidate {
    private String candidate;
    private String sdpMid;
    private int sdpMLineIndex;
    private String usernameFragment;
}