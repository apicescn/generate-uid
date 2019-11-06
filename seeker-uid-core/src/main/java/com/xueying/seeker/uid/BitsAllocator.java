/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xueying.seeker.uid;

import com.xueying.seeker.uid.contants.UIDContants;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.Assert;

import static com.xueying.seeker.uid.contants.UIDContants.SIX_CONSTANT_INT;

/**
 * Allocate 64 bits for the UID(long)<br>
 * sign (fixed 1bit) -> deltaSecond -> workerId -> sequence(within the same second)
 * 
 * @author yutianbao
 */
@Data
@ToString
public class BitsAllocator {
    /**
     * Total 64 bits
     */
    public static final int TOTAL_BITS = 1 << SIX_CONSTANT_INT;

    /**
     * Bits for [sign-> second-> workId-> sequence]
     */
    private int signBits = 1;
    /**
     * 时间位
     */
    private final int timestampBits;
    /**
     * 机器位
     */
    private final int workerIdBits;
    /**
     * 序列号
     */
    private final int sequenceBits;

    /**
     * Max value for workId & sequence
     */
    private final long maxDeltaSeconds;
    /**
     * 最大机器位
     */
    private final long maxWorkerId;
    /**
     * 最大序列位
     */
    private final long maxSequence;

    /**
     * Shift for timestamp & workerId
     */
    private final int timestampShift;
    /**
     * Shift for workerId
     */
    private final int workerIdShift;

    /**
     * Constructor with timestampBits, workerIdBits, sequenceBits<br>
     * The highest bit used for sign, so <code>63</code> bits for timestampBits, workerIdBits, sequenceBits
     */
    public BitsAllocator(int timestampBits, int workerIdBits, int sequenceBits) {
        // make sure allocated 64 bits
        int allocateTotalBits = signBits + timestampBits + workerIdBits + sequenceBits;
        Assert.isTrue(allocateTotalBits == TOTAL_BITS, "allocate not enough 64 bits");

        // initialize bits
        this.timestampBits = timestampBits;
        this.workerIdBits = workerIdBits;
        this.sequenceBits = sequenceBits;

        // initialize max value
        this.maxDeltaSeconds = ~(UIDContants.MINUS_ONE_CONSTANT_LONG << timestampBits);
        this.maxWorkerId = ~(UIDContants.MINUS_ONE_CONSTANT_LONG << workerIdBits);
        this.maxSequence = ~(UIDContants.MINUS_ONE_CONSTANT_LONG << sequenceBits);

        // initialize shift
        this.timestampShift = workerIdBits + sequenceBits;
        this.workerIdShift = sequenceBits;
    }

    /**
     * Allocate bits for UID according to delta seconds & workerId & sequence<br>
     * <b>Note that: </b>The highest bit will always be 0 for sign
     * 
     * @param deltaSeconds 时间位
     * @param workerId 机器位
     * @param sequence 序号们
     * @return 根据三个参数分配对应的Uid
     */
    public long allocate(long deltaSeconds, long workerId, long sequence) {
        return (deltaSeconds << timestampShift) | (workerId << workerIdShift) | sequence;
    }
    
}