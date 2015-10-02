// Auto generated. Do not edit directly!
/*
 * Copyright (c) 2015. Suminda Sirinath Salpitikorala Dharmasena
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.susico.utils.arrays.tabled.arrayshort.mutable;

import com.susico.utils.arrays.tabled.arrayshort.immutable.ImmutableTabledArrayShort;

public abstract class MutableTabledArrayShort extends ImmutableTabledArrayShort {
    protected MutableTabledArrayShort(final boolean checked, final int definedAsValues, final int length, final short ... values) {
        super(checked, definedAsValues, length, values);
    }


    public abstract void put(final int index, final short value);

    protected final void putToRest(final int index, final short value) {
        ARRAY_ACCESS.put(rest, index - definedAsValues, value);
    }

    public static  MutableTabledArrayShort getInstance(final boolean checked, final int length, final short ... values) {
        switch (length) {
            case 0:
                return MutableTabledArray0000Short.getInstance(checked, length, values);
            case 1:
                return MutableTabledArray0001Short.getInstance(checked, length, values);
            case 2: 
                return MutableTabledArray0002Short.getInstance(checked, length, values);

            case 3: case 4: 
                return MutableTabledArray0004Short.getInstance(checked, length, values);

            case 5: case 6: case 7: case 8: 
                return MutableTabledArray0008Short.getInstance(checked, length, values);

            case 9: case 10: case 11: case 12: case 13: case 14: case 15: 
            case 16: 
                return MutableTabledArray0016Short.getInstance(checked, length, values);

            case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 31: 
            case 32: 
                return MutableTabledArray0032Short.getInstance(checked, length, values);

            case 33: case 34: case 35: case 36: case 37: case 38: case 39: case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: 
            case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59: case 60: case 61: case 62: case 63: 
            case 64: 
                return MutableTabledArray0064Short.getInstance(checked, length, values);

            case 65: case 66: case 67: case 68: case 69: case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79: 
            case 80: case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89: case 90: case 91: case 92: case 93: case 94: case 95: 
            case 96: case 97: case 98: case 99: case 100: case 101: case 102: case 103: case 104: case 105: case 106: case 107: case 108: case 109: case 110: case 111: 
            case 112: case 113: case 114: case 115: case 116: case 117: case 118: case 119: case 120: case 121: case 122: case 123: case 124: case 125: case 126: case 127: 
            case 128: 
                return MutableTabledArray0128Short.getInstance(checked, length, values);

            case 129: case 130: case 131: case 132: case 133: case 134: case 135: case 136: case 137: case 138: case 139: case 140: case 141: case 142: case 143: 
            case 144: case 145: case 146: case 147: case 148: case 149: case 150: case 151: case 152: case 153: case 154: case 155: case 156: case 157: case 158: case 159: 
            case 160: case 161: case 162: case 163: case 164: case 165: case 166: case 167: case 168: case 169: case 170: case 171: case 172: case 173: case 174: case 175: 
            case 176: case 177: case 178: case 179: case 180: case 181: case 182: case 183: case 184: case 185: case 186: case 187: case 188: case 189: case 190: case 191: 
            case 192: case 193: case 194: case 195: case 196: case 197: case 198: case 199: case 200: case 201: case 202: case 203: case 204: case 205: case 206: case 207: 
            case 208: case 209: case 210: case 211: case 212: case 213: case 214: case 215: case 216: case 217: case 218: case 219: case 220: case 221: case 222: case 223: 
            case 224: case 225: case 226: case 227: case 228: case 229: case 230: case 231: case 232: case 233: case 234: case 235: case 236: case 237: case 238: case 239: 
            case 240: case 241: case 242: case 243: case 244: case 245: case 246: case 247: case 248: case 249: case 250: case 251: case 252: case 253: case 254: case 255: 
            case 256: 
                return MutableTabledArray0256Short.getInstance(checked, length, values);

            case 257: case 258: case 259: case 260: case 261: case 262: case 263: case 264: case 265: case 266: case 267: case 268: case 269: case 270: case 271: 
            case 272: case 273: case 274: case 275: case 276: case 277: case 278: case 279: case 280: case 281: case 282: case 283: case 284: case 285: case 286: case 287: 
            case 288: case 289: case 290: case 291: case 292: case 293: case 294: case 295: case 296: case 297: case 298: case 299: case 300: case 301: case 302: case 303: 
            case 304: case 305: case 306: case 307: case 308: case 309: case 310: case 311: case 312: case 313: case 314: case 315: case 316: case 317: case 318: case 319: 
            case 320: case 321: case 322: case 323: case 324: case 325: case 326: case 327: case 328: case 329: case 330: case 331: case 332: case 333: case 334: case 335: 
            case 336: case 337: case 338: case 339: case 340: case 341: case 342: case 343: case 344: case 345: case 346: case 347: case 348: case 349: case 350: case 351: 
            case 352: case 353: case 354: case 355: case 356: case 357: case 358: case 359: case 360: case 361: case 362: case 363: case 364: case 365: case 366: case 367: 
            case 368: case 369: case 370: case 371: case 372: case 373: case 374: case 375: case 376: case 377: case 378: case 379: case 380: case 381: case 382: case 383: 
            case 384: case 385: case 386: case 387: case 388: case 389: case 390: case 391: case 392: case 393: case 394: case 395: case 396: case 397: case 398: case 399: 
            case 400: case 401: case 402: case 403: case 404: case 405: case 406: case 407: case 408: case 409: case 410: case 411: case 412: case 413: case 414: case 415: 
            case 416: case 417: case 418: case 419: case 420: case 421: case 422: case 423: case 424: case 425: case 426: case 427: case 428: case 429: case 430: case 431: 
            case 432: case 433: case 434: case 435: case 436: case 437: case 438: case 439: case 440: case 441: case 442: case 443: case 444: case 445: case 446: case 447: 
            case 448: case 449: case 450: case 451: case 452: case 453: case 454: case 455: case 456: case 457: case 458: case 459: case 460: case 461: case 462: case 463: 
            case 464: case 465: case 466: case 467: case 468: case 469: case 470: case 471: case 472: case 473: case 474: case 475: case 476: case 477: case 478: case 479: 
            case 480: case 481: case 482: case 483: case 484: case 485: case 486: case 487: case 488: case 489: case 490: case 491: case 492: case 493: case 494: case 495: 
            case 496: case 497: case 498: case 499: case 500: case 501: case 502: case 503: case 504: case 505: case 506: case 507: case 508: case 509: case 510: case 511: 
            case 512: 
                return MutableTabledArray0512Short.getInstance(checked, length, values);

            case 513: case 514: case 515: case 516: case 517: case 518: case 519: case 520: case 521: case 522: case 523: case 524: case 525: case 526: case 527: 
            case 528: case 529: case 530: case 531: case 532: case 533: case 534: case 535: case 536: case 537: case 538: case 539: case 540: case 541: case 542: case 543: 
            case 544: case 545: case 546: case 547: case 548: case 549: case 550: case 551: case 552: case 553: case 554: case 555: case 556: case 557: case 558: case 559: 
            case 560: case 561: case 562: case 563: case 564: case 565: case 566: case 567: case 568: case 569: case 570: case 571: case 572: case 573: case 574: case 575: 
            case 576: case 577: case 578: case 579: case 580: case 581: case 582: case 583: case 584: case 585: case 586: case 587: case 588: case 589: case 590: case 591: 
            case 592: case 593: case 594: case 595: case 596: case 597: case 598: case 599: case 600: case 601: case 602: case 603: case 604: case 605: case 606: case 607: 
            case 608: case 609: case 610: case 611: case 612: case 613: case 614: case 615: case 616: case 617: case 618: case 619: case 620: case 621: case 622: case 623: 
            case 624: case 625: case 626: case 627: case 628: case 629: case 630: case 631: case 632: case 633: case 634: case 635: case 636: case 637: case 638: case 639: 
            case 640: case 641: case 642: case 643: case 644: case 645: case 646: case 647: case 648: case 649: case 650: case 651: case 652: case 653: case 654: case 655: 
            case 656: case 657: case 658: case 659: case 660: case 661: case 662: case 663: case 664: case 665: case 666: case 667: case 668: case 669: case 670: case 671: 
            case 672: case 673: case 674: case 675: case 676: case 677: case 678: case 679: case 680: case 681: case 682: case 683: case 684: case 685: case 686: case 687: 
            case 688: case 689: case 690: case 691: case 692: case 693: case 694: case 695: case 696: case 697: case 698: case 699: case 700: case 701: case 702: case 703: 
            case 704: case 705: case 706: case 707: case 708: case 709: case 710: case 711: case 712: case 713: case 714: case 715: case 716: case 717: case 718: case 719: 
            case 720: case 721: case 722: case 723: case 724: case 725: case 726: case 727: case 728: case 729: case 730: case 731: case 732: case 733: case 734: case 735: 
            case 736: case 737: case 738: case 739: case 740: case 741: case 742: case 743: case 744: case 745: case 746: case 747: case 748: case 749: case 750: case 751: 
            case 752: case 753: case 754: case 755: case 756: case 757: case 758: case 759: case 760: case 761: case 762: case 763: case 764: case 765: case 766: case 767: 
            case 768: case 769: case 770: case 771: case 772: case 773: case 774: case 775: case 776: case 777: case 778: case 779: case 780: case 781: case 782: case 783: 
            case 784: case 785: case 786: case 787: case 788: case 789: case 790: case 791: case 792: case 793: case 794: case 795: case 796: case 797: case 798: case 799: 
            case 800: case 801: case 802: case 803: case 804: case 805: case 806: case 807: case 808: case 809: case 810: case 811: case 812: case 813: case 814: case 815: 
            case 816: case 817: case 818: case 819: case 820: case 821: case 822: case 823: case 824: case 825: case 826: case 827: case 828: case 829: case 830: case 831: 
            case 832: case 833: case 834: case 835: case 836: case 837: case 838: case 839: case 840: case 841: case 842: case 843: case 844: case 845: case 846: case 847: 
            case 848: case 849: case 850: case 851: case 852: case 853: case 854: case 855: case 856: case 857: case 858: case 859: case 860: case 861: case 862: case 863: 
            case 864: case 865: case 866: case 867: case 868: case 869: case 870: case 871: case 872: case 873: case 874: case 875: case 876: case 877: case 878: case 879: 
            case 880: case 881: case 882: case 883: case 884: case 885: case 886: case 887: case 888: case 889: case 890: case 891: case 892: case 893: case 894: case 895: 
            case 896: case 897: case 898: case 899: case 900: case 901: case 902: case 903: case 904: case 905: case 906: case 907: case 908: case 909: case 910: case 911: 
            case 912: case 913: case 914: case 915: case 916: case 917: case 918: case 919: case 920: case 921: case 922: case 923: case 924: case 925: case 926: case 927: 
            case 928: case 929: case 930: case 931: case 932: case 933: case 934: case 935: case 936: case 937: case 938: case 939: case 940: case 941: case 942: case 943: 
            case 944: case 945: case 946: case 947: case 948: case 949: case 950: case 951: case 952: case 953: case 954: case 955: case 956: case 957: case 958: case 959: 
            case 960: case 961: case 962: case 963: case 964: case 965: case 966: case 967: case 968: case 969: case 970: case 971: case 972: case 973: case 974: case 975: 
            case 976: case 977: case 978: case 979: case 980: case 981: case 982: case 983: case 984: case 985: case 986: case 987: case 988: case 989: case 990: case 991: 
            case 992: case 993: case 994: case 995: case 996: case 997: case 998: case 999: case 1000: case 1001: case 1002: case 1003: case 1004: case 1005: case 1006: case 1007: 
            case 1008: case 1009: case 1010: case 1011: case 1012: case 1013: case 1014: case 1015: case 1016: case 1017: case 1018: case 1019: case 1020: case 1021: case 1022: case 1023: 
            case 1024: 
                return MutableTabledArray1024Short.getInstance(checked, length, values);


            default:
                return MutableTabledArray1024Short.getInstance(checked, length, values);
        }
    }
}