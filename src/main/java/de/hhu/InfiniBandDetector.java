package de.hhu;

import de.hhu.bsinfo.jdetector.lib.IbFabric;
import de.hhu.bsinfo.jdetector.lib.IbNode;
import de.hhu.bsinfo.jdetector.lib.IbPort;
import de.hhu.bsinfo.jdetector.lib.exception.IbFileException;
import de.hhu.bsinfo.jdetector.lib.exception.IbMadException;
import de.hhu.bsinfo.jdetector.lib.exception.IbNetDiscException;
import de.hhu.bsinfo.jdetector.lib.exception.IbVerbsException;

public class InfiniBandDetector {
    public void collectInfos() {
        boolean network = true;
        boolean compatibility = true;
        IbFabric fabric = null;

        try {
            fabric = new IbFabric(network, compatibility);
        } catch (IbFileException | IbMadException | IbVerbsException |
                IbNetDiscException e) {
            e.printStackTrace();
        }

        for (IbNode node : fabric.getNodes()) {
            for (IbPort port : node.getPorts()) {
                System.out.println(node.getDescription() + " (port " + port.getNum() + "): " + port.getXmitDataBytes() + " Bytes");
            }
        }
    }
}
