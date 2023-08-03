import sys

def main():
    if len(sys.argv) < 2:
        print("Uso: python3 teste.py <argumento>")
        return

    argumento = sys.argv[1]

    print(f": Olá {argumento}! Seja bem vindo ao JenkinsLib")

if __name__ == "__main__":
    main()
