/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.openmuc.jsml.structures;

public class SmlTimestampLocal extends Sequence {

    private SmlTimestamp timestamp;
    private Integer16 localOffset;
    private Integer16 seasonTimeOffset;

    public SmlTimestampLocal() {
    }

    public SmlTimestampLocal(SmlTimestamp timestamp, Integer16 localOffset, Integer16 seasonTimeOffset) {

        if (timestamp == null) {
            throw new IllegalArgumentException("SML_TimestampLocal: timestamp is not optional and must not be null!");
        }
        if (localOffset == null) {
            throw new IllegalArgumentException("SML_TimestampLocal: localOffset is not optional and must not be null!");
        }
        if (seasonTimeOffset == null) {
            throw new IllegalArgumentException(
                    "SML_TimestampLocal: seasonTimeOffset is not optional and must not be null!");
        }

        this.timestamp = timestamp;
        this.localOffset = localOffset;
        this.seasonTimeOffset = seasonTimeOffset;

        setOptionalAndSeq();
        setSelected(true);
    }

    public SmlTimestamp getTimestamp() {
        return timestamp;
    }

    public Integer16 getLocalOffset() {
        return localOffset;
    }

    public Integer16 getSeasonTimeOffset() {
        return seasonTimeOffset;
    }

    public void setOptionalAndSeq() {
        seqArray(new ASNObject[] { timestamp, localOffset, seasonTimeOffset });
    }

    @Override
    protected void createElements() {
        timestamp = new SmlTimestamp();
        localOffset = new Integer16();
        seasonTimeOffset = new Integer16();
        setOptionalAndSeq();
    }

}
