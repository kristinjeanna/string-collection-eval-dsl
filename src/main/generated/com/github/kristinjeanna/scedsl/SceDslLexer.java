// Generated from com/github/kristinjeanna/scedsl/SceDsl.g4 by ANTLR 4.10.1
package com.github.kristinjeanna.scedsl;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SceDslLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, OR=2, XOR=3, NOT=4, CONTAINS=5, STARTSWITH=6, ENDSWITH=7, TRUE=8, 
		FALSE=9, STRING=10, SINGLE_QUOTE=11, COMMA=12, LPAREN=13, RPAREN=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"AND", "OR", "XOR", "NOT", "CONTAINS", "STARTSWITH", "ENDSWITH", "TRUE", 
			"FALSE", "STRING", "SINGLE_QUOTE", "COMMA", "LPAREN", "RPAREN", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'and'", "'or'", "'xor'", "'not'", "'contains'", "'startsWith'", 
			"'endsWith'", "'true'", "'false'", null, "'''", "','", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "OR", "XOR", "NOT", "CONTAINS", "STARTSWITH", "ENDSWITH", 
			"TRUE", "FALSE", "STRING", "SINGLE_QUOTE", "COMMA", "LPAREN", "RPAREN", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SceDslLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SceDsl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000fj\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0004\t"+
		"X\b\t\u000b\t\f\tY\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\u000e\u0004\u000ee\b\u000e\u000b\u000e\f\u000e"+
		"f\u0001\u000e\u0001\u000e\u0000\u0000\u000f\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u0001\u0000\u0002"+
		"\u0002\u0000AZaz\u0003\u0000\t\n\r\r  k\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0001"+
		"\u001f\u0001\u0000\u0000\u0000\u0003#\u0001\u0000\u0000\u0000\u0005&\u0001"+
		"\u0000\u0000\u0000\u0007*\u0001\u0000\u0000\u0000\t.\u0001\u0000\u0000"+
		"\u0000\u000b7\u0001\u0000\u0000\u0000\rB\u0001\u0000\u0000\u0000\u000f"+
		"K\u0001\u0000\u0000\u0000\u0011P\u0001\u0000\u0000\u0000\u0013W\u0001"+
		"\u0000\u0000\u0000\u0015[\u0001\u0000\u0000\u0000\u0017]\u0001\u0000\u0000"+
		"\u0000\u0019_\u0001\u0000\u0000\u0000\u001ba\u0001\u0000\u0000\u0000\u001d"+
		"d\u0001\u0000\u0000\u0000\u001f \u0005a\u0000\u0000 !\u0005n\u0000\u0000"+
		"!\"\u0005d\u0000\u0000\"\u0002\u0001\u0000\u0000\u0000#$\u0005o\u0000"+
		"\u0000$%\u0005r\u0000\u0000%\u0004\u0001\u0000\u0000\u0000&\'\u0005x\u0000"+
		"\u0000\'(\u0005o\u0000\u0000()\u0005r\u0000\u0000)\u0006\u0001\u0000\u0000"+
		"\u0000*+\u0005n\u0000\u0000+,\u0005o\u0000\u0000,-\u0005t\u0000\u0000"+
		"-\b\u0001\u0000\u0000\u0000./\u0005c\u0000\u0000/0\u0005o\u0000\u0000"+
		"01\u0005n\u0000\u000012\u0005t\u0000\u000023\u0005a\u0000\u000034\u0005"+
		"i\u0000\u000045\u0005n\u0000\u000056\u0005s\u0000\u00006\n\u0001\u0000"+
		"\u0000\u000078\u0005s\u0000\u000089\u0005t\u0000\u00009:\u0005a\u0000"+
		"\u0000:;\u0005r\u0000\u0000;<\u0005t\u0000\u0000<=\u0005s\u0000\u0000"+
		"=>\u0005W\u0000\u0000>?\u0005i\u0000\u0000?@\u0005t\u0000\u0000@A\u0005"+
		"h\u0000\u0000A\f\u0001\u0000\u0000\u0000BC\u0005e\u0000\u0000CD\u0005"+
		"n\u0000\u0000DE\u0005d\u0000\u0000EF\u0005s\u0000\u0000FG\u0005W\u0000"+
		"\u0000GH\u0005i\u0000\u0000HI\u0005t\u0000\u0000IJ\u0005h\u0000\u0000"+
		"J\u000e\u0001\u0000\u0000\u0000KL\u0005t\u0000\u0000LM\u0005r\u0000\u0000"+
		"MN\u0005u\u0000\u0000NO\u0005e\u0000\u0000O\u0010\u0001\u0000\u0000\u0000"+
		"PQ\u0005f\u0000\u0000QR\u0005a\u0000\u0000RS\u0005l\u0000\u0000ST\u0005"+
		"s\u0000\u0000TU\u0005e\u0000\u0000U\u0012\u0001\u0000\u0000\u0000VX\u0007"+
		"\u0000\u0000\u0000WV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000"+
		"YW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\u0014\u0001\u0000"+
		"\u0000\u0000[\\\u0005\'\u0000\u0000\\\u0016\u0001\u0000\u0000\u0000]^"+
		"\u0005,\u0000\u0000^\u0018\u0001\u0000\u0000\u0000_`\u0005(\u0000\u0000"+
		"`\u001a\u0001\u0000\u0000\u0000ab\u0005)\u0000\u0000b\u001c\u0001\u0000"+
		"\u0000\u0000ce\u0007\u0001\u0000\u0000dc\u0001\u0000\u0000\u0000ef\u0001"+
		"\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000"+
		"gh\u0001\u0000\u0000\u0000hi\u0006\u000e\u0000\u0000i\u001e\u0001\u0000"+
		"\u0000\u0000\u0003\u0000Yf\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}